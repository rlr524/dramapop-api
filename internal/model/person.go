/*
internal/model/person.go
Created 1/25/25
Rob Ranf
rob@emiyaconsulting.com
- Data model representing a person (actor, actress, writer, director...)
*/

package model

import "time"

type Person struct {
	ID        int64     `json:"id"`
	CreatedAt time.Time `json:"created_at"`
	UpdatedAt time.Time `json:"updated_at"`
	FirstName string    `json:"first_name"`
	LastName  string    `json:"last_name"`
	Dramas    []Drama   `json:"dramas"`
	Deleted   bool      `default:"false" json:"deleted"`
	Version   int32     `json:"version"`
}
