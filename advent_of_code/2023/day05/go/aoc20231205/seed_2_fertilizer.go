package main

import (
	"log"
	"math"
	"os"
	"slices"
	"strconv"
	"strings"
)

const SEEDS_PREFIX = "seeds: "
const SEED_TO_SOIL = "seed-to-soil"
const SOIL_TO_FERTILIZER = "soil-to-fertilizer"
const FERTILIZER_TO_WATER = "fertilizer-to-water"
const WATER_TO_LIGHT = "water-to-light"
const LIGHT_TO_TEMPERATURE = "light-to-temperature"
const TEMPERATURE_TO_HUMIDITY = "temperature-to-humidity"
const HUMIDITY_TO_LOCATION = "humidity-to-location"

var namesOfMaps = []string{
	SEED_TO_SOIL,
	SOIL_TO_FERTILIZER,
	FERTILIZER_TO_WATER,
	WATER_TO_LIGHT,
	LIGHT_TO_TEMPERATURE,
	TEMPERATURE_TO_HUMIDITY,
	HUMIDITY_TO_LOCATION,
}

func extractData(filename string) ([]uint64, map[string][]AssociatedRange) {
	var seedData []uint64
	associatedRangesByName := make(map[string][]AssociatedRange)

	filebuffer, err := os.ReadFile(filename)

	if err != nil {
		log.Fatal(err)
	}

	inputData := strings.Split(string(filebuffer), "\n")

	//log.Println("Data extracted successfully:\n", inputData)
	inputDataSize := len(inputData)

	for i, line := range inputData {
		// Seeds extraction
		if strings.Contains(line, SEEDS_PREFIX) {
			seedsLine := strings.TrimPrefix(line, SEEDS_PREFIX)
			seeds := strings.Split(seedsLine, " ")
			//log.Println("Extracted seeds:", seeds)

			for _, seed := range seeds {
				seed = strings.TrimSpace(seed)
				seedInt, err := strconv.ParseUint(seed, 10, 64)
				if err != nil {
					log.Fatal("Error converting seed to integer:", err)
				}
				seedData = append(seedData, seedInt)
			}
			log.Println("Extracted seed integers:", seedData)
		}

		// Creating the map of AssociatedRanges
		for _, mapName := range namesOfMaps {
			if strings.Contains(line, mapName) {
				associatedRanges := []AssociatedRange{}

				for j := i + 1; j < inputDataSize && len(strings.TrimSpace(inputData[j])) > 0; j++ {
					//log.Printf("Extracted values for %s: %v\n", mapName, strings.TrimSpace(inputData[j]))
					parts := strings.Split(strings.TrimSpace(inputData[j]), " ")
					if len(parts) != 3 {
						log.Fatal("Invalid format for " + mapName + ": " + strings.Join(parts, ", "))
					}
					source, err := strconv.ParseUint(parts[1], 10, 64)
					if err != nil {
						log.Fatal("Error converting source to integer for " + mapName + ": " + parts[1])
					}
					destination, err := strconv.ParseUint(parts[0], 10, 64)
					if err != nil {
						log.Fatal("Error converting destination to integer for " + mapName + ": " + parts[0])
					}
					valuesRange, err := strconv.ParseUint(parts[2], 10, 64)
					if err != nil {
						log.Fatal("Error converting range to integer for " + mapName + ": " + parts[2])
					}

					ar := AssociatedRange{
						Source:      source,
						Destination: destination,
						Range:       valuesRange,
					}

					associatedRanges = append(associatedRanges, ar)

				}
				associatedRangesByName[mapName] = associatedRanges
			}
		}
	}
	return seedData, associatedRangesByName
}

// Must do step by step, seeds to soil, and so on until location

func seedToSoil(seed uint64, associatedRangesByName map[string][]AssociatedRange) uint64 {
	associatedValue := seed
	for _, ar := range associatedRangesByName[SEED_TO_SOIL] {
		if ar.In(seed) {
			associatedValue, _ = ar.GetAssociatedValue(seed)
		}
	}
	//log.Printf("Seed %d maps to Soil %d\n", seed, associatedValue)
	return associatedValue
}

func soilToFertilizer(soil uint64, associatedRangesByName map[string][]AssociatedRange) uint64 {
	associatedValue := soil
	for _, ar := range associatedRangesByName[SOIL_TO_FERTILIZER] {
		if ar.In(soil) {
			associatedValue, _ = ar.GetAssociatedValue(soil)
		}
	}
	//log.Printf("Soil %d maps to Fertilizer %d\n", soil, associatedValue)
	return associatedValue
}

func fertilizerToWater(fertilizer uint64, associatedRangesByName map[string][]AssociatedRange) uint64 {
	associatedValue := fertilizer
	for _, ar := range associatedRangesByName[FERTILIZER_TO_WATER] {
		if ar.In(fertilizer) {
			associatedValue, _ = ar.GetAssociatedValue(fertilizer)
		}
	}
	//log.Printf("Fertilizer %d maps to Water %d\n", fertilizer, associatedValue)
	return associatedValue
}

func waterToLight(water uint64, associatedRangesByName map[string][]AssociatedRange) uint64 {
	associatedValue := water
	for _, ar := range associatedRangesByName[WATER_TO_LIGHT] {
		if ar.In(water) {
			associatedValue, _ = ar.GetAssociatedValue(water)
		}
	}
	//log.Printf("Water %d maps to Light %d\n", water, associatedValue)
	return associatedValue
}

func lightToTemperature(light uint64, associatedRangesByName map[string][]AssociatedRange) uint64 {
	associatedValue := light
	for _, ar := range associatedRangesByName[LIGHT_TO_TEMPERATURE] {
		if ar.In(light) {
			associatedValue, _ = ar.GetAssociatedValue(light)
		}
	}
	//log.Printf("Light %d maps to Temperature %d\n", light, associatedValue)
	return associatedValue
}

func temperatureToHumidity(temperature uint64, associatedRangesByName map[string][]AssociatedRange) uint64 {
	associatedValue := temperature
	for _, ar := range associatedRangesByName[TEMPERATURE_TO_HUMIDITY] {
		if ar.In(temperature) {
			associatedValue, _ = ar.GetAssociatedValue(temperature)
		}
	}
	//log.Printf("Temperature %d maps to Humidity %d\n", temperature, associatedValue)
	return associatedValue
}

func humidityToLocation(humidity uint64, associatedRangesByName map[string][]AssociatedRange) uint64 {
	associatedValue := humidity
	for _, ar := range associatedRangesByName[HUMIDITY_TO_LOCATION] {
		if ar.In(humidity) {
			associatedValue, _ = ar.GetAssociatedValue(humidity)
		}
	}
	//log.Printf("Humidity %d maps to Location %d\n", humidity, associatedValue)
	return associatedValue
}

func seedsToLocation(seedNumbers []uint64, associatedRangesByName map[string][]AssociatedRange) []uint64 {
	var locations []uint64
	for _, seed := range seedNumbers {
		soil := seedToSoil(seed, associatedRangesByName)
		fertilizer := soilToFertilizer(soil, associatedRangesByName)
		water := fertilizerToWater(fertilizer, associatedRangesByName)
		light := waterToLight(water, associatedRangesByName)
		temperature := lightToTemperature(light, associatedRangesByName)
		humidity := temperatureToHumidity(temperature, associatedRangesByName)
		location := humidityToLocation(humidity, associatedRangesByName)
		locations = append(locations, location)
	}
	return locations
}

func seedRangesToMinLocation(seedRanges []SeedRange, associatedRangesByName map[string][]AssociatedRange) uint64 {
	var minLocation uint64 = math.MaxUint64
	for _, sr := range seedRanges {
		for seed := range sr.Seeds() {
			soil := seedToSoil(seed, associatedRangesByName)
			fertilizer := soilToFertilizer(soil, associatedRangesByName)
			water := fertilizerToWater(fertilizer, associatedRangesByName)
			light := waterToLight(water, associatedRangesByName)
			temperature := lightToTemperature(light, associatedRangesByName)
			humidity := temperatureToHumidity(temperature, associatedRangesByName)
			location := humidityToLocation(humidity, associatedRangesByName)
			if location < minLocation {
				minLocation = location
			}
		}
	}
	return minLocation
}

func seedRangesFromSeedData(seedData []uint64) []SeedRange {
	dataSize := len(seedData)
	if dataSize%2 != 0 {
		log.Fatal("Invalid seed data length, must be even")
	}

	var seedRanges []SeedRange
	for i := 0; i < dataSize; i += 2 {
		start := seedData[i]
		rangeSize := seedData[i+1]
		seedRanges = append(seedRanges, SeedRange{Source: start, Range: rangeSize})
	}
	return seedRanges
}

func GetMinimumLocationForPart1(filename string) uint64 {
	seeds, associatedRangesByName := extractData(filename)
	locations := seedsToLocation(seeds, associatedRangesByName)
	minLocation := slices.Min(locations)
	return minLocation
}

func GetMinimumLocationForPart2(filename string) uint64 {
	seedData, associatedRangesByName := extractData(filename)
	seedRanges := seedRangesFromSeedData(seedData)
	minLocation := seedRangesToMinLocation(seedRanges, associatedRangesByName)
	return minLocation
}
