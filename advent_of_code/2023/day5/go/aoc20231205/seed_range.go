package main

import "iter"

type SeedRange struct {
	Source uint64
	Range  uint64
}

func (sr *SeedRange) Seeds() iter.Seq[uint64] {
	return func(yield func(uint64) bool) {
		for i := uint64(0); i < sr.Range; i++ {
			if !yield(sr.Source + i) {
				return
			}
		}
	}
}
