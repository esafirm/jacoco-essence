package nolambda.essence.diff

class GitDiff(private val commandExecutor: (String) -> String? = DefaultCommandExecutor()) {

    companion object {
        private const val DEFAULT_DEST_BRANCH = "master"
    }

    fun createGitDif(destinationBranch: String? = DEFAULT_DEST_BRANCH): String? {
        val destination = destinationBranch ?: DEFAULT_DEST_BRANCH
        val base = commandExecutor.invoke("git merge-base $destination HEAD")?.trim()
        return commandExecutor.invoke("git diff --name-only $base")
    }
}