#!/usr/bin/env groovy

def call() {
    echo "prepareEnv function was called successfully!"
    return [branch: "test-branch", commit: "test-commit"]
}
