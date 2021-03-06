package xyz.qwewqa.relive.simulator.client

import kotlinx.browser.document
import kotlinx.dom.clear
import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.js.onClickFunction
import org.w3c.dom.*
import xyz.qwewqa.relive.simulator.common.InteractiveLogData
import xyz.qwewqa.relive.simulator.common.LogEntry
import xyz.qwewqa.relive.simulator.common.LogCategory

val LogCategory.backgroundColor
    get() = when (this) {
        LogCategory.DEFAULT -> "#F5F5F5"
        LogCategory.EMPHASIS -> "#E8EAF6"
        LogCategory.WARNING -> "#FFF9C4"
        LogCategory.ERROR -> "#F8BBD0"
        LogCategory.DAMAGE -> "#FFEBEE"
        LogCategory.HEAL -> "#E8F5E9"
        LogCategory.BUFF -> "#E3F2FD"
        LogCategory.BRILLIANCE -> "#FFF8E1"
        LogCategory.USER -> "#E0F7FA"
        LogCategory.COMMAND -> "#ECEFF1"
        else -> "#FFFFFF"
    }

private var idCounter = 0


fun HTMLElement.displayStatus(data: InteractiveLogData) {
    val bossWidth = when (data.enemyStatus?.size) {
        1 -> 12
        2 -> 6
        3 -> 4
        4 -> 6
        5 -> 4
        else -> 4
    }
    val playerWidth = when (data.playerStatus?.size) {
        1 -> 12
        2 -> 6
        3 -> 4
        4 -> 6
        5 -> 4
        else -> 4
    }
    if (
        children[0]?.children?.length == (data.enemyStatus?.size ?: 0) &&
        children[1]?.children?.length == (data.playerStatus?.size ?: 0)
    ) {
        data.enemyStatus?.reversed()?.forEachIndexed { i, status ->
            val nameElement = document.getElementById("boss-name-$i") as HTMLParagraphElement
            val hpElement = document.getElementById("boss-hp-$i") as HTMLDivElement
            val hpLabelElement = document.getElementById("boss-hp-label-$i") as HTMLDivElement
            nameElement.textContent = status.name
            hpElement.style.width = "${status.hp.toDouble() / status.maxHp * 100}%"
            if (status.hp > 0) {
                hpElement.textContent = "${status.hp}/${status.maxHp}"
                hpLabelElement.textContent = "${status.hp}/${status.maxHp}"
            } else {
                hpElement.textContent = ""
                hpLabelElement.textContent = ""
            }
        }
        val totalDamage = data.playerStatus?.sumOf { it.damageContribution } ?: 0
        data.playerStatus?.reversed()?.forEachIndexed { i, status ->
            val nameElement = document.getElementById("player-name-$i") as HTMLParagraphElement
            val hpElement = document.getElementById("player-hp-$i") as HTMLDivElement
            val hpLabelElement = document.getElementById("player-hp-label-$i") as HTMLDivElement
            val brillianceElement = document.getElementById("player-brilliance-$i") as HTMLDivElement
            val brillianceLabelElement = document.getElementById("player-brilliance-label-$i") as HTMLDivElement
            val damageElement = document.getElementById("player-damage-$i") as HTMLDivElement
            val damageLabelElement = document.getElementById("player-damage-label-$i") as HTMLDivElement
            nameElement.textContent = status.name
            hpElement.style.width = "${status.hp.toDouble() / status.maxHp * 100}%"
            brillianceElement.style.width = "${status.brilliance}%"
            damageElement.style.width = "${status.damageContribution.toDouble() / totalDamage.coerceAtLeast(1) * 100}%"
            if (status.hp > 0) {
                hpElement.textContent = "${status.hp}/${status.maxHp}"
                hpLabelElement.textContent = "${status.hp}/${status.maxHp}"
                brillianceElement.textContent = "${status.brilliance}"
                brillianceLabelElement.textContent = "${status.brilliance}"
            } else {
                hpElement.textContent = ""
                hpLabelElement.textContent = ""
                brillianceElement.textContent = ""
                brillianceLabelElement.textContent = ""
            }
            damageElement.textContent = "${status.damageContribution}"
            damageLabelElement.textContent = "${status.damageContribution}"
        }
    } else {
        clear()
        append {
            div(classes = "row justify-content-evenly") {
                // Reversed since frontmost is the first in the list
                data.enemyStatus?.reversed()?.forEachIndexed { i, status ->
                    div(classes = "col-$bossWidth") {
                        p(classes = "mt-1 mb-0") {
                            id = "boss-name-$i"
                            style = "font-size: 0.75rem;"
                            +status.name
                        }
                        div(classes = "progress") {
                            style = "font-size: 0.7rem;height: 0.8rem;position: relative;"
                            div(classes = "progress-bar bg-danger") {
                                id = "boss-hp-$i"
                                style =
                                    "width: ${status.hp.toDouble() / status.maxHp * 100}%;font-weight: bold;z-index: 2;"
                                if (status.hp > 0) {
                                    +"${status.hp}/${status.maxHp}"
                                }
                            }
                            div {
                                id = "boss-hp-label-$i"
                                style =
                                    "font-weight: bold;color: black;position: absolute;left: 0;top: 0;z-index: 1;height: 100%;display: flex;flex-direction:column;justify-content: center;align-items: center;"
                                if (status.hp > 0) {
                                    +"${status.hp}/${status.maxHp}"
                                }
                            }
                        }
                    }
                }
            }
            div(classes = "row justify-content-evenly") {
                val totalDamage = data.playerStatus?.sumOf { it.damageContribution } ?: 0
                data.playerStatus?.reversed()?.forEachIndexed { i, status ->
                    div(classes = "col-$playerWidth") {
                        p(classes = "mt-1 mb-0") {
                            id = "player-name-$i"
                            style = "font-size: 0.75rem;"
                            +status.name
                        }
                        div(classes = "progress") {
                            style = "font-size: 0.7rem;height: 0.8rem;position: relative;"
                            div(classes = "progress-bar bg-success") {
                                id = "player-hp-$i"
                                style =
                                    "width: ${status.hp.toDouble() / status.maxHp * 100}%;font-weight: bold;z-index: 2;"
                                if (status.hp > 0) {
                                    +"${status.hp}/${status.maxHp}"
                                }
                            }
                            div {
                                id = "player-hp-label-$i"
                                style =
                                    "font-weight: bold;color: black;position: absolute;left: 0;top: 0;z-index: 1;height: 100%;display: flex;flex-direction:column;justify-content: center;align-items: center;"
                                if (status.hp > 0) {
                                    +"${status.hp}/${status.maxHp}"
                                }
                            }
                        }
                        div(classes = "progress") {
                            style = "font-size: 0.7rem;height: 0.8rem;margin-top: 0.1rem;position: relative;"
                            div(classes = "progress-bar bg-warning") {
                                id = "player-brilliance-$i"
                                style = "width: ${status.brilliance}%;color: black;font-weight: bold;z-index: 2;"
                                if (status.hp > 0) {
                                    +"${status.brilliance}"
                                }
                            }
                            div {
                                id = "player-brilliance-label-$i"
                                style =
                                    "font-weight: bold;color: black;position: absolute;left: 0;top: 0;z-index: 1;height: 100%;display: flex;flex-direction:column;justify-content: center;align-items: center;"
                                if (status.hp > 0) {
                                    +"${status.brilliance}"
                                }
                            }
                        }
                        div(classes = "progress d-none damage-shares") {
                            style = "font-size: 0.7rem;height: 0.8rem;margin-top: 0.1rem;position: relative;"
                            div(classes = "progress-bar bg-info") {
                                id = "player-damage-$i"
                                style =
                                    "width: ${status.damageContribution.toDouble() / totalDamage.coerceAtLeast(1) * 100}%;color: black;font-weight: bold;z-index: 2;"
                                if (status.damageContribution > 0) {
                                    +"${status.damageContribution}"
                                }
                            }
                            div {
                                id = "player-damage-label-$i"
                                style =
                                    "font-weight: bold;color: black;position: absolute;left: 0;top: 0;z-index: 1;height: 100%;display: flex;flex-direction:column;justify-content: center;align-items: center;"
                                if (status.damageContribution > 0) {
                                    +"${status.damageContribution}"
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun HTMLElement.displayLog(log: List<LogEntry>, interactive: Boolean) {
    clear()

    val lastSummaryIndex = log.indexOfLast { it.summary != null }.takeIf { interactive }
    append {
        log.forEachIndexed { i, entry ->
            entry.run {
                div(classes = "log-entry") {
                    style = "background-color: ${category.backgroundColor};"
                    when {
                        summary != null -> {
                            val collapseId = "log-contents-${idCounter++}"
                            span {
                                b {
                                    +"$turn.$tile.$move [${tags.joinToString(" / ")}]"
                                }
                                +" $summary "
                                if (i != lastSummaryIndex) {
                                    span {
                                        +"["
                                        a {
                                            role = "button"
                                            href = "#"
                                            attributes["data-bs-toggle"] = "collapse"
                                            attributes["data-bs-target"] = "#$collapseId"
                                            +"details"
                                        }
                                        +"]"
                                    }
                                }
                            }
                            div(classes = if (i != lastSummaryIndex) "collapse" else "") {
                                id = collapseId
                                span {
                                    style = "padding-left: 1em;display: block;white-space: pre;overflow-x: auto;"
                                    processLogContent(content)
                                }
                            }
                        }
                        content.contains("\n") -> {
                            span {
                                b {
                                    +"$turn.$tile.$move [${tags.joinToString(" / ")}]"
                                }
                            }
                            span {
                                style = "padding-left: 1em;display: block;white-space: pre;overflow-x: auto;"
                                processLogContent(content)
                            }
                        }
                        else -> {
                            span {
                                b {
                                    +"$turn.$tile.$move [${tags.joinToString(" / ")}]"
                                }
                                +" "
                                processLogContent(content)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Suppress("RegExpRedundantEscape") // JS doesn't like the unescaped ]
private val replacementRegex = """@(\[(.*?)\])?\((.*?):(.*?)\)""".toRegex()

private fun FlowOrInteractiveOrPhrasingContent.processLogContent(content: String) {
    val split = content.split(replacementRegex)

    replacementRegex.findAll(content).forEachIndexed { index, match ->
        +split[index] // Add the previous split

        val disp = match.groups[2]?.value ?: ""
        val name = match.groups[3]!!.value.trim()
        val data = match.groups[4]!!.value.trim()

        fun imageReplacement(url: (Int) -> String) {
            val id = data.toIntOrNull()
            if (id != null && id != -1) {
                img {
                    style = "height: 1.4em;margin: -0.25em 0.05em;"
                    src = url(id)
                }
            }
        }

        when (name) {
            "dress" -> imageReplacement { "img/large_icon/1_$it.png" }
            "memoir" -> imageReplacement { "img/large_icon/2_$it.png" }
            "act" -> imageReplacement { "img/skill_icon/skill_icon_$it.png" }
            "command" -> a(href = "#") {
                title = data
                onClickFunction = { e ->
                    document.dispatchEvent(CustomEvent("sendInteractiveCommand", jsObject { detail = data }))
                    e.preventDefault()
                }
                +disp
            }
            else -> {}
        }
    }

    +split.last()
}
