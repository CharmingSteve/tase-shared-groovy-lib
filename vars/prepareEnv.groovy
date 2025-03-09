#!/usr/bin/env groovy

def call() {
    // Create map to store build info
    def buildInfo = [:]
    
    // Get basic info from environment variables (safe approach)
    buildInfo.branch = env.BRANCH_NAME ?: env.GIT_BRANCH ?: "unknown"
    buildInfo.buildNumber = env.BUILD_NUMBER ?: "unknown"
    buildInfo.buildUrl = env.BUILD_URL ?: "unknown"
    
    // Get commit info safely
    try {
        buildInfo.commitId = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
        buildInfo.shortCommit = buildInfo.commitId.take(7)
        buildInfo.committer = sh(script: 'git log -1 --pretty=%an', returnStdout: true).trim()
        buildInfo.commitMsg = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
    } catch (Exception e) {
        echo "Could not get git information: ${e.message}"
        buildInfo.commitId = "unknown"
        buildInfo.shortCommit = "unknown"
        buildInfo.committer = "unknown"
        buildInfo.commitMsg = "unknown"
    }
    
    // Log the information
    echo "Build Information:"
    echo "Branch: ${buildInfo.branch}"
    echo "Commit: ${buildInfo.commitId}"
    echo "Committer: ${buildInfo.committer}"
    echo "Build: #${buildInfo.buildNumber}"
    
    return buildInfo
}
