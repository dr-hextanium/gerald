package org.firstinspires.ftc.teamcode.command.auto

import com.pedropathing.follower.Follower
import com.pedropathing.pathgen.PathChain
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.command.CommandTemplate

class PedroPathCommand(
	val path: PathChain,
	val follower: Follower,
	val time: ElapsedTime = ElapsedTime(),
	val finished: () -> Boolean = { follower.isBusy }
) : CommandTemplate() {
	override fun initialize() = follower.followPath(path, true)

	override fun execute() = follower.update()

	override fun isFinished() = finished()
}