import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import nolambda.essence.diff.GitDiff

class GitDiffSpec : StringSpec({

    val mockCommandExecutor = mockk<(String) -> String?>(relaxed = true)
    val gitDiff = GitDiff(mockCommandExecutor)

    "it should run correct command" {
        val returnValue = "src/main/ClassToTest.kt\nsrc/main/ClassToTest2.kt"
        every { mockCommandExecutor.invoke(any()) } returns returnValue

        val diff = gitDiff.createGitDif()

        diff shouldBe returnValue
        verifySequence {
            mockCommandExecutor.invoke("git merge-base master HEAD")
            mockCommandExecutor.invoke("git diff --name-only $returnValue")
        }
    }

    "diff should correctly separated by lines" {
        val returnValue = "src/main/ClassToTest.kt\nsrc/main/ClassToTest2.kt"
        returnValue.lines().size shouldBe 2
    }
})