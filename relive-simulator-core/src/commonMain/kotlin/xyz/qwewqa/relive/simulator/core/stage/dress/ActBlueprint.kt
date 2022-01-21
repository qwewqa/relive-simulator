package xyz.qwewqa.relive.simulator.core.stage.dress

import xyz.qwewqa.relive.simulator.core.stage.Act
import xyz.qwewqa.relive.simulator.core.stage.actor.ActData
import xyz.qwewqa.relive.simulator.core.stage.actor.ActType

data class ActBlueprint(
    val name: String,
    val type: ActType,
    val apCost: Int,
    val icon: Int? = null,
    val parameters: List<ActParameters>,
    val value: ActBlueprintContext.() -> Act = { Act {} },
) {
    fun create(level: Int): ActData {
        return ActData(
            name,
            type,
            apCost,
            icon,
            ActBlueprintContext(
                level,
                parameters.map { it.values[level - 1] },
                parameters.map { it.times[level - 1] },
            ).value()
        )
    }
}

fun ActType.blueprint(
    name: String,
    apCost: Int,
    icon: Int? = null,
    vararg parameters: ActParameters,
    value: ActBlueprintContext.() -> Act,
) = ActBlueprint(
    name,
    this,
    apCost,
    icon,
    parameters.toList(),
    value,
)

data class ActParameters(
    val values: List<Int> = emptyList(),
    val times: List<Int> = emptyList(),
)

class ActBlueprintContext(
    val level: Int,
    private val values: List<Int>,
    private val times: List<Int>,
) {
    // To match up with Karth more easily
    val values1 get() = values[0]
    val times1 get() = times[0]
    val values2 get() = values[1]
    val times2 get() = times[1]
    val values3 get() = values[2]
    val times3 get() = times[2]
    val values4 get() = values[3]
    val times4 get() = times[3]
    val values5 get() = values[4]
    val times5 get() = times[4]
}