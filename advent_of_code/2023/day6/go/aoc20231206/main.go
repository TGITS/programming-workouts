package main

import (
	"log"
)

func main() {
	log.Printf("Product of record breakings for part 1: %d\n", RecordBreakingsProducts("data/input.txt"))
	log.Printf("Number of ways to win for part 2: %d\n", RecordBreaking("data/input.txt"))
}
