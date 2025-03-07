def call() {
    def buildInfo = [:]
    
    // Get SCM information
    buildInfo.branch = env.BRANCH_NAME ?: env.GIT_BRANCH ?: "Unknown branch"
    buildInfo.commitId = env.GIT_COMMIT ?: "Unknown commit"
    
    // Only run git commands if we're in a git repository
    try {
        buildInfo.commitMsg = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
        buildInfo.committer = sh(script: 'git log -1 --pretty=%an', returnStdout: true).trim()
        buildInfo.committerEmail = sh(script: 'git log -1 --pretty=%ae', returnStdout: true).trim()
    } catch (Exception e) {
        buildInfo.commitMsg = "Unknown message"
        buildInfo.committer = "Unknown committer"
        buildInfo.committerEmail = "Unknown email"
    }
    
    buildInfo.buildNumber = env.BUILD_NUMBER ?: "Unknown build"
    buildInfo.buildUrl = env.BUILD_URL ?: "Unknown URL"
    
    // Print the information (useful for logs)
    echo "Branch: ${buildInfo.branch}"
    echo "Commit ID: ${buildInfo.commitId}"
    echo "Commit Message: ${buildInfo.commitMsg}"
    echo "Committer: ${buildInfo.committer}"
    echo "Committer Email: ${buildInfo.committerEmail}"
    echo "Build Number: ${buildInfo.buildNumber}"
    echo "Build URL: ${buildInfo.buildUrl}"
    
    return buildInfo
}
