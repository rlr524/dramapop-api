/*
internal/model/drama.go
Created 12/29/24
Rob Ranf
rob@emiyaconsulting.com
- A short description of what this file does*
*/

package model

import "time"

type Drama struct {
	ID        int64     `json:"id"`
	CreatedAt time.Time `json:"created_at"`
	UpdatedAt time.Time `json:"updated_at"`
	Title     string    `json:"title"`
	Year      int32     `json:"year"`
	Episodes  int32     `json:"episodes"`
	Runtime   int32     `json:"runtime"`
	Genres    []string  `json:"genres"` // TODO: Add slice of genres using Genre entity
	Studio    string    `json:"studio"`
	// TODO: Add slice of actors using Person entity
	Deleted bool  `default:"false" json:"deleted"`
	Version int32 `json:"version"`
}
