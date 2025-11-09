package main

type AssociatedRange struct {
	Source      uint64
	Destination uint64
	Range       uint64
}

func (ar *AssociatedRange) In(value uint64) bool {
	return value >= ar.Source && value < ar.Source+ar.Range
}

func (ar *AssociatedRange) GetAssociatedValue(value uint64) (uint64, bool) {
	if ar.In(value) {
		return ar.Destination + (value - ar.Source), true
	}
	return 0, false
}
