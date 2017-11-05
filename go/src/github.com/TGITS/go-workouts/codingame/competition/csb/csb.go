package main

import (
	"fmt"
	"math"
	"math/cmplx"
	"os"
	"sort"
	"strconv"
)

const (
	CheckpointCoreSize = 50
	//MaxLoopBeforeBoost = 3
	//MaxLoopBeforeShield = 10
	PodRadius        = 200
	CheckpointRadius = 600
)

// ToRadians convert the parameters corresponding to an angle in degrees int an angle in radians.
// radians  = degrees * PI / 180
func ToRadians(degrees float64) float64 { return float64(degrees) * math.Pi / 180.0 }

// ToDegrees convert the parameters corresponding to an angle in radians int an angle in degrees.
// degreess = radians * 180 / PI
func ToDegrees(radians float64) float64 { return float64(radians) * 180.0 / math.Pi }

// Round rounds a float64, converts it to an int and returns it
func Round(val float64) int {
	if val < 0 {
		return int(val - 0.5)
	}
	return int(val + 0.5)
}

func computeAngle(a complex128, b complex128, hypothenuse float64) (float64, float64, float64) {
	c := complex(real(a), imag(b))
	// hypothenuse := cmplx.Abs(a - b)
	cosTheta := cmplx.Abs(c-b) / hypothenuse
	sinTheta := cmplx.Abs(a-c) / hypothenuse
	theta := ToDegrees(math.Acos(cosTheta))
	return cosTheta, sinTheta, theta
}

// TurnInfo is a struct that gather all the game information of a turn
type TurnInfo struct {
	Checkpoint  complex128
	PlayerPod   complex128
	OpponentPod complex128
	Angle       int //Angle between the player pod and the next Checkpoint in degrees
	Distance    int //Distance between the player pod and the next Checkpoint

}

// NewTurnInfo to create and initialize a new TurnInfo (a pointer to TurnInfo)
func NewTurnInfo(x, y, nextCheckpointX, nextCheckpointY, nextCheckpointDist, nextCheckpointAngle, opponentX, opponentY int) *TurnInfo {
	turnInfo := new(TurnInfo)
	turnInfo.Checkpoint = complex(float64(nextCheckpointX), float64(nextCheckpointY))
	turnInfo.PlayerPod = complex(float64(x), float64(y))
	turnInfo.OpponentPod = complex(float64(opponentX), float64(opponentY))
	turnInfo.Angle = nextCheckpointAngle
	turnInfo.Distance = nextCheckpointDist
	return turnInfo
}

/*
computeThrust compute the thrust value
*/
func (turnInfo *TurnInfo) computeThrust(realDistance int) string {
	switch {
	case math.Abs(float64(turnInfo.Angle)) >= 90 || realDistance < 600:
		return "0"
	case realDistance < 801:
		return "20"
	case math.Abs(float64(turnInfo.Angle)) >= 72 || realDistance < 1001:
		return "40"
	case realDistance < 1201:
		return "60"
	case realDistance < 1501:
		return "80"
	// case math.Abs(float64(turnInfo.Angle)) >= 72:
	// 	return "40"
	// case math.Abs(float64(turnInfo.Angle)) >= 54:
	// 	return "40"
	// case math.Abs(float64(turnInfo.Angle)) >= 36:
	// 	return "60"
	// case math.Abs(float64(turnInfo.Angle)) >= 18:
	// 	return "80"
	default:
		return "100"
	}
}

// func (turnInfo *TurnInfo) computeTarget() (string, string) {
// 	return strconv.Itoa(int(real(turnInfo.Checkpoint))), strconv.Itoa(int(imag(turnInfo.Checkpoint)))
// }

func (turnInfo *TurnInfo) computeTarget() (string, string, int) {
	// intermediate := complex(real(pod), imag(checkpoint))
	// hypothenuse := cmplx.Abs(pod - checkpoint)
	// cosTheta := cmplx.Abs(intermediate-checkpoint) / hypothenuse
	// sinTheta := cmplx.Abs(pod-intermediate) / hypothenuse
	//cosTheta, sinTheta, theta := computeAngle(pod, checkpoint, float64(nextCheckpointDistance))
	cosTheta, sinTheta, _ := computeAngle(turnInfo.PlayerPod, turnInfo.Checkpoint, float64(turnInfo.Distance))
	// fmt.Fprintf(os.Stderr, "cosTheta : %.1f\n", cosTheta)
	// fmt.Fprintf(os.Stderr, "sinTheta : %.1f\n", sinTheta)
	// fmt.Fprintf(os.Stderr, "theta : %.1f\n", theta)
	// fmt.Fprintf(os.Stderr, "hypothenuse : %.1f\n", hypothenuse)
	var xTarget, yTarget float64
	distances := make([]float64, 5, 5)
	angles := make([]float64, 5, 5)
	targetsByDistance := make(map[float64]complex128)
	targetsByAngle := make(map[float64]complex128)

	distances[0] = float64(turnInfo.Distance)
	angles[0] = math.Abs(float64(turnInfo.Angle))
	targetsByDistance[distances[0]] = turnInfo.Checkpoint
	targetsByAngle[angles[0]] = turnInfo.Checkpoint
	index := 1
	for i := 0; i < 2; i++ {
		for j := 0; j < 2; j++ {
			if i%2 != 0 {
				xTarget = real(turnInfo.Checkpoint) - PodRadius*cosTheta
			} else {
				xTarget = real(turnInfo.Checkpoint) + PodRadius*cosTheta
			}

			if j%2 != 0 {
				yTarget = imag(turnInfo.Checkpoint) + PodRadius*sinTheta
			} else {
				yTarget = imag(turnInfo.Checkpoint) - PodRadius*sinTheta
			}
			target := complex(xTarget, yTarget)
			distances[index] = cmplx.Abs(turnInfo.PlayerPod - target)
			_, _, angle := computeAngle(turnInfo.PlayerPod, target, distances[index])
			targetsByDistance[distances[index]] = target
			angles[index] = math.Abs(angle)
			targetsByAngle[angles[index]] = target
			index++
		}
	}
	sort.Float64s(distances)
	// sort.Float64s(angles)
	// fmt.Fprintf(os.Stderr, "Distances from pod to potential target : %.1f\n", distances)
	// fmt.Fprintf(os.Stderr, "targetsByDistance : %v\n", targetsByDistance)
	// fmt.Fprintf(os.Stderr, "Absolute value of the angle between pod and potential target : %.1f\n", angles)
	// fmt.Fprintf(os.Stderr, "targetsByAngle : %v\n", targetsByAngle)
	// return strconv.Itoa(int(real(targetsByAngle[angles[0]]))), strconv.Itoa(int(imag(targetsByAngle[angles[0]])))
	return strconv.Itoa(int(real(targetsByDistance[distances[0]]))), strconv.Itoa(int(imag(targetsByDistance[distances[0]]))), int(distances[0])
}

// GameInfo is a struct that gather all the global game information relative to the game
type GameInfo struct {
	Checkpoints    []complex128
	History        map[int]*TurnInfo
	Boost          bool //True if a boost is available, false if the thrust of the player pod has already been used
	Shield         int  //To mark if the shield has been used. With possible value 0 (not recently used or more than 3 turns ; the initial value), 3 (just used), 2 (used 1 turn ago), 1 (used 2 turns ago)
	Turn           int  // 0 is the initial value
	Lap            int  //The lap in which we are. This value has to be computed
	LastCheckpoint complex128
}

// NewGameInfo To create and initialize a new GameInfo (a pointer to GameInfo)
// Essentially the game board for CSB in silver league is a slice of checkpoints
// This slice is poupulated turns by turns, we know we are in the second lap when we cannot had a new CP
// We know we are in the third lap when we have reached the first checkpoint
func NewGameInfo() *GameInfo {
	gameInfo := new(GameInfo)
	gameInfo.Boost = true
	gameInfo.Shield = 3
	gameInfo.Turn = 0
	gameInfo.Lap = 0
	gameInfo.Checkpoints = make([]complex128, 0, 10)
	gameInfo.History = make(map[int]*TurnInfo)
	gameInfo.LastCheckpoint = 0 + 0i
	return gameInfo
}

// UpdateHistory is a method that had a new TurnInfo in the GameInfo. Before it update the turn count, the checkpoint list and the Lap count if necessary
func (gameInfo *GameInfo) UpdateHistory(turnInfo *TurnInfo) {
	gameInfo.UpdateTurn()
	// if gameInfo.Lap == 0 {
	gameInfo.UpdateCheckpoints(turnInfo.Checkpoint)
	// }
	gameInfo.History[gameInfo.Turn] = turnInfo
}

// PreviousTurn is a method that return the counter value of the previous turn
func (gameInfo *GameInfo) PreviousTurn() int {
	return gameInfo.Turn - 1
}

// NextTurn is a method that return the counter value of the next turn
func (gameInfo *GameInfo) NextTurn() int {
	return gameInfo.Turn + 1
}

// UpdateTurn is a method that updates the turn counter and return an int representing this new value
func (gameInfo *GameInfo) UpdateTurn() {
	gameInfo.Turn++
}

// UpdateCheckpoints is a method that update the list of checkpoints if necessary
func (gameInfo *GameInfo) UpdateCheckpoints(checkpoint complex128) {
	if checkpoint != gameInfo.LastCheckpoint {
		newCheckpoint, newLap := gameInfo.In(checkpoint)
		if gameInfo.Lap == 0 && !newCheckpoint {
			fmt.Fprintf(os.Stderr, "Append %.1f to %v\n", checkpoint, gameInfo.Turn)
			gameInfo.Checkpoints = append(gameInfo.Checkpoints, checkpoint)
		}
		if newLap {
			fmt.Fprintf(os.Stderr, "New Lap\n")
			gameInfo.Lap++
		}
		gameInfo.LastCheckpoint = checkpoint
	}
}

// In is a method that checks if the complex number is in the slice Checkpoints and if it is a new turn
func (gameInfo *GameInfo) In(complex complex128) (bool, bool) {
	lastIndex := len(gameInfo.Checkpoints) - 1
	for index, value := range gameInfo.Checkpoints {
		if complex == value {
			if index == lastIndex {
				return true, true
			}
			return true, false
		}
		if index == lastIndex {
			return false, true
		}
		return false, false
	}
	return false, false
}

func (gameInfo *GameInfo) updateBoost() {
	gameInfo.Boost = false
}

func (gameInfo *GameInfo) getCurrentTurnInfo() *TurnInfo {
	return gameInfo.History[gameInfo.Turn]
}

// DumpInfo is a method to dump the information on stderr
func (gameInfo *GameInfo) DumpInfo() {
	fmt.Fprintf(os.Stderr, "Turn : %d\n", gameInfo.Turn)
	fmt.Fprintf(os.Stderr, "Lap : %d\n", gameInfo.Lap)
	fmt.Fprintf(os.Stderr, "Checkpoints : %v\n", gameInfo.Checkpoints)
	fmt.Fprintf(os.Stderr, "Boost available ? : %t\n", gameInfo.Boost)
	turnInfo := gameInfo.getCurrentTurnInfo()
	fmt.Fprintf(os.Stderr, "Checkpoint coordinates : %.1f\n", turnInfo.Checkpoint)
	fmt.Fprintf(os.Stderr, "Player Pod coordinates : %.1f\n", turnInfo.PlayerPod)
	fmt.Fprintf(os.Stderr, "Opponent pod coordinates : %.1f\n", turnInfo.OpponentPod)
	fmt.Fprintf(os.Stderr, "Angle in degrees between pod and next checkpoint : %d\n", turnInfo.Angle)
	fmt.Fprintf(os.Stderr, "Distance from pod to next checkpoint : %d\n", turnInfo.Distance)
	fmt.Fprintf(os.Stderr, "History : %v\n", gameInfo.History)
}

func (gameInfo *GameInfo) computeValues() (string, string, string) {
	turnInfo := gameInfo.getCurrentTurnInfo()
	xTarget, yTarget, realDistance := turnInfo.computeTarget()
	return xTarget, yTarget, gameInfo.computeAction(realDistance)
}

/*
computeAction return "BOOST", "SHIELD" or the thrust value
*/
func (gameInfo *GameInfo) computeAction(realDistance int) string {
	turnInfo := gameInfo.getCurrentTurnInfo()
	if gameInfo.Boost && math.Abs(float64(turnInfo.Angle)) < 10 && realDistance > 4000 {
		gameInfo.updateBoost()
		return "BOOST"
	}
	return turnInfo.computeThrust(realDistance)
}

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

func main() {

	var xTarget, yTarget, thrust string
	gameInfo := NewGameInfo()
	for {
		// nextCheckpointX: x position of the next check point
		// nextCheckpointY: y position of the next check point
		// nextCheckpointDist: distance to the next checkpoint
		// nextCheckpointAngle: angle between your pod orientation and the direction of the next checkpoint
		var x, y, nextCheckpointX, nextCheckpointY, nextCheckpointDist, nextCheckpointAngle int
		fmt.Scan(&x, &y, &nextCheckpointX, &nextCheckpointY, &nextCheckpointDist, &nextCheckpointAngle)

		var opponentX, opponentY int
		fmt.Scan(&opponentX, &opponentY)

		turnInfo := NewTurnInfo(x, y, nextCheckpointX, nextCheckpointY, nextCheckpointDist, nextCheckpointAngle, opponentX, opponentY)
		gameInfo.UpdateHistory(turnInfo)

		gameInfo.DumpInfo()

		// You have to output the target position
		// followed by the power (0 <= thrust <= 100) or "BOOST" or "SHIELD"
		// i.e.: "x y thrust"
		xTarget, yTarget, thrust = gameInfo.computeValues()
		// thrustByTurn[turns] = thrust
		fmt.Printf("%s %s %s\n", xTarget, yTarget, thrust)
	}
}
package main

import (
	"fmt"
	"math"
	"math/cmplx"
	"strconv"
)

const (
	CheckpointCoreSize  = 50
	MaxLoopBeforeBoost  = 3
	MaxLoopBeforeShield = 10
	PodRadius           = 400
	CheckpointRadius    = 600
)

// ToRadians convert the parameters corresponding to an angle in degrees int an angle in radians.
// radians  = degrees * PI / 180
func ToRadians(angleInDegrees float64) float64 { return angleInDegrees * math.Pi / 180 }

// ToDegrees convert the parameters corresponding to an angle in radians int an angle in degrees.
// degreess = radians * 180 / PI
func ToDegrees(angleInRadians float64) float64 { return angleInRadians * 180 / math.Pi }

//Round a float64 and convert it to an int32
func round(val float64) int32 {
	if val < 0 {
		return int32(val - 0.5)
	}
	return int32(val + 0.5)
}

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

func main() {

	// checkpoints := make(map[complex128]struct{})
	var checkpoint complex128
	var pod complex128
	var opponentPod complex128
	var xTarget, yTarget, thrust string
	// var checkpointAngle, podAngle, opponentPodAngle, angleBetweenPods float64
	turns := 0
	boostUsed := new(bool)
	*boostUsed = false
	// thrustByTurn := make(map[int]string)
	// thrustByTurn[turns] = "0"
	for {
		// nextCheckpointX: x position of the next check point
		// nextCheckpointY: y position of the next check point
		// nextCheckpointDist: distance to the next checkpoint
		// nextCheckpointAngle: angle between your pod orientation and the direction of the next checkpoint
		var x, y, nextCheckpointX, nextCheckpointY, nextCheckpointDist, nextCheckpointAngle int
		fmt.Scan(&x, &y, &nextCheckpointX, &nextCheckpointY, &nextCheckpointDist, &nextCheckpointAngle)

		var opponentX, opponentY int
		fmt.Scan(&opponentX, &opponentY)

		turns++
		pod = complex(float64(x), float64(y))
		checkpoint = complex(float64(nextCheckpointX), float64(nextCheckpointY))
		opponentPod = complex(float64(opponentX), float64(opponentY))
		// if _, ok := checkpoints[checkpoint]; ok != true {
		// 	checkpoints[checkpoint] = struct{}{}
		// }

		// fmt.Fprintln(os.Stderr, "Debug messages...")
		// fmt.Fprintf(os.Stderr, "Turn number : %d\n", turns)
		//fmt.Fprintf(os.Stderr, "Pod coordinates : %.1f\n", pod)
		// fmt.Fprintf(os.Stderr, "Opponent pod coordinates : %.1f\n", opponentPod)
		//fmt.Fprintf(os.Stderr, "Checkpoint coordinates : %.1f\n", checkpoint)
		//fmt.Fprintf(os.Stderr, "Angle in degrees between pod and next checkpoint : %d\n", nextCheckpointAngle)
		// fmt.Fprintf(os.Stderr, "List of checkpoints : %v\n", checkpoints)
		// fmt.Fprintf(os.Stderr, "Number of checkpoints : %v\n", len(checkpoints))
		// fmt.Fprintf(os.Stderr, "Boost use ? : %t\n", *boostUsed)
		//fmt.Fprintf(os.Stderr, "Distance from pod to next checkpoint : %d\n", nextCheckpointDist)
		// fmt.Fprintf(os.Stderr, "Calculated distance between pod and checkpoint : %f\n", cmplx.Abs(checkpoint-pod))
		// fmt.Fprintf(os.Stderr, "Calculated distance between opponent pod and checkpoint : %f\n", cmplx.Abs(checkpoint-opponentPod))
		// fmt.Fprintf(os.Stderr, "Calculated distance between pod and opponent pod : %f\n", cmplx.Abs(opponentPod-pod))

		// _, checkpointAngle = cmplx.Polar(checkpoint)
		// _, podAngle = cmplx.Polar(pod)
		// _, opponentPodAngle = cmplx.Polar(opponentPod)
		// fmt.Fprintf(os.Stderr, "Calculated phases in radians for checkpoint, pod and opponentPod : %.1f %.1f %.1f\n", checkpointAngle, podAngle, opponentPodAngle)
		// _, angleBetweenPods = cmplx.Polar((pod - checkpoint) / (opponentPod - checkpoint))
		// fmt.Fprintf(os.Stderr, "Calculated angle in radians between the 2 vectors (Checkpoint, Pod) and (Checkpoint, OpponentPod) : %.3f\n", angleBetweenPods)
		// fmt.Fprintf(os.Stderr, "Calculated angle in degrees between the 2 vectors (Checkpoint, Pod) and (Checkpoint, OpponentPod) : %.3f\n", ToDegrees(angleBetweenPods))
		//ToDegrees
		// You have to output the target position
		// followed by the power (0 <= thrust <= 100) or "BOOST" or "SHIELD"
		// i.e.: "x y thrust"
		xTarget, yTarget, thrust = computeValues(checkpoint, nextCheckpointDist, nextCheckpointAngle, pod, opponentPod, boostUsed, turns)
		// thrustByTurn[turns] = thrust
		fmt.Printf("%s %s %s\n", xTarget, yTarget, thrust)
	}
}

func computeValues(checkpoint complex128, nextCheckpointDistance int, nextCheckpointAngle int, pod complex128, opponentPod complex128, boostUsed *bool, turns int) (string, string, string) {
	target := computeTarget(checkpoint, nextCheckpointDistance, nextCheckpointAngle, pod)
	return strconv.Itoa(int(real(target))), strconv.Itoa(int(imag(target))), computeAction(checkpoint, nextCheckpointDistance, nextCheckpointAngle, pod, opponentPod, boostUsed, turns)
	//return strconv.Itoa(int(real(checkpoint))), strconv.Itoa(int(imag(checkpoint))), computeAction(checkpoint, nextCheckpointDistance, nextCheckpointAngle, pod, opponentPod, boostUsed, turns)
}

/*
computeAction return "BOOST", "SHIELD" or the thrust value
*/
func computeAction(checkpoint complex128, nextCheckpointDistance int, nextCheckpointAngle int, pod complex128, opponentPod complex128, boostUsed *bool, turns int) string {
	if !*boostUsed && turns > MaxLoopBeforeBoost && math.Abs(float64(nextCheckpointAngle)) < 10 && nextCheckpointDistance > 4000 {
		*boostUsed = true
		return "BOOST"
	}
	return computeThrust(checkpoint, nextCheckpointDistance, nextCheckpointAngle, pod, opponentPod, boostUsed, turns)
}

/*
computeThrust compute the thrust value
*/
func computeThrust(checkpoint complex128, nextCheckpointDistance int, nextCheckpointAngle int, pod complex128, opponentPod complex128, boostUsed *bool, turns int) string {
	switch {
	// case *boostUsed && turns > 10 && cmplx.Abs(opponentPod-pod) <= 900 && math.Abs(float64(nextCheckpointAngle)) > 90:
	// 	return "SHIELD"
	case math.Abs(float64(nextCheckpointAngle)) >= 90 || (nextCheckpointDistance < 1000 && math.Abs(float64(nextCheckpointAngle)) >= 25) /* || nextCheckpointDistance < 600*/ :
		return "0"
	// case nextCheckpointDistance < 600 && math.Abs(float64(nextCheckpointAngle)) > 45:
	// 	return "0"
	case nextCheckpointDistance < 600 /* || (nextCheckpointDistance < 1000 && math.Abs(float64(nextCheckpointAngle)) > 45)*/ :
		return "20"
	case nextCheckpointDistance < 1000:
		return "40"
	case nextCheckpointDistance < 2000:
		return "80"
	// case nextCheckpointDistance < 1200:
	// 	return "60"
	// case nextCheckpointDistance < 1500:
	// 	return "80"
	// case nextCheckpointDistance < 100 && math.Abs(float64(nextCheckpointAngle)) > 25:
	// 	return "0"
	// case nextCheckpointDistance < 1000:
	// 	return "50"
	// case nextCheckpointDistance < 2000:
	// 	return "90"
	default:
		return "100"
	}
}

func computeTarget(checkpoint complex128, nextCheckpointDistance int, nextCheckpointAngle int, pod complex128) complex128 {
	if nextCheckpointDistance > 800 {
		intermediate := complex(real(pod), imag(checkpoint))
		hypothenuse := cmplx.Abs(pod - checkpoint)
		cosTheta := cmplx.Abs(intermediate-checkpoint) / hypothenuse
		sinTheta := cmplx.Abs(pod-intermediate) / hypothenuse
		var xTarget, yTarget float64
		if real(checkpoint) > real(pod) {
			xTarget = real(checkpoint) - PodRadius*cosTheta
			//xTarget = real(checkpoint) + PodRadius*cosTheta
		} else {
			xTarget = real(checkpoint) + PodRadius*cosTheta
			//xTarget = real(checkpoint) - PodRadius*cosTheta
		}
		if imag(checkpoint) > imag(pod) {
			yTarget = imag(checkpoint) - PodRadius*sinTheta
			//yTarget = imag(checkpoint) + PodRadius*sinTheta
		} else {
			yTarget = imag(checkpoint) + PodRadius*sinTheta
			//yTarget = imag(checkpoint) - PodRadius*sinTheta
		}
		return complex(xTarget, yTarget)
	}
	return checkpoint
}
