package main

import (
	"github.com/joho/godotenv"
	"log/slog"
	"os"
)

const version = "1.0.0"

type application struct {
	logger *slog.Logger
}

var db = make(map[string]string)

func main() {
	logger := slog.New(slog.NewTextHandler(os.Stdout, nil))

	err := godotenv.Load()
	if err != nil {
		logger.Error(err.Error())
		os.Exit(1)
	}
	port := os.Getenv("PORT")

	r := setupRouter()
	err = r.Run(port)
	if err != nil {
		logger.Error(err.Error())
		os.Exit(1)
	}
}
