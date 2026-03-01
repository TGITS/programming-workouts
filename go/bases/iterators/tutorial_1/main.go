package main

import (
	"fmt"
	"iter"
	"time"
)

// iter.Seq[T] <=> func(func(T) bool)
func Countdown(v int) iter.Seq[int] {
	return func(yield func(int) bool) {
		for i := v; i >= 0; i-- {
			if !yield(i) {
				return
			}
		}
	}
}

type Employee struct {
	Name   string
	Salary int
}

var Employees = []Employee{
	{Name: "Alice", Salary: 50000},
	{Name: "Bob", Salary: 60000},
	{Name: "Charlie", Salary: 55000},
	{Name: "Elliot", Salary: 40000},
	{Name: "Donna", Salary: 50000},
}

// iter.Seq2[K,V] <=> func(func(K, V) bool)
func EmployeeIterator(e []Employee) iter.Seq2[int, Employee] {
	return func(yield func(int, Employee) bool) {
		for i := 0; i < len(e); i++ {
			if !yield(i, e[i]) {
				return
			}
		}
	}
}

// iter.Seq2 <=> func(func(int, E) bool)
func Iterate[E any](e []E) iter.Seq2[int, E] {
	return func(yield func(int, E) bool) {
		for i := 0; i < len(e); i++ {
			time.Sleep(5 * time.Second)
			if !yield(i, e[i]) {
				return
			}
		}
	}
}

func main() {
	for i := range Countdown(5) {
		fmt.Printf("Countdown: %d\n", i)
	}

	for i, e := range EmployeeIterator(Employees) {
		fmt.Printf("Employee %d: %s, Salary: %d\n", i, e.Name, e.Salary)
	}

	for i, val := range Iterate(Employees) {
		fmt.Printf("Iterate Employee %d: %s, Salary: %d\n", i, val.Name, val.Salary)
	}
}
