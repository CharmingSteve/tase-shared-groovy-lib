# README for tase-shared-groovy-lib

## Jenkins Shared Library for TASE Pipeline

This repository contains a shared Groovy library for Jenkins pipelines, specifically created for the Tel Aviv Stock Exchange DevOps assignment.

### Purpose

This shared library provides reusable functions that can be imported into Jenkins pipelines. The main function is `prepareEnv()`, which extracts SCM information such as:

- Commit ID
- Committer name
- Branch name
- Commit message

### Structure

```
tase-shared-groovy-lib/
└── vars/
    └── prepareEnv.groovy    # Function to extract SCM information
```

### Usage

To use this shared library in a Jenkins pipeline, add the following to your Jenkinsfile:

```groovy
@Library('tase-shared-groovy-lib@main') _

pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                script {
                    def buildInfo = prepareEnv()
                    echo "Committed by: ${buildInfo.committer}"
                }
            }
        }
    }
}
```

### Implementation

For a complete implementation example, see the main project repository: [tase-groovy](https://github.com/CharmingSteve/tase-groovy)

