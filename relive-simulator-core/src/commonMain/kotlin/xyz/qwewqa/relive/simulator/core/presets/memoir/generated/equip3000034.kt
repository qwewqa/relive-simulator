package xyz.qwewqa.relive.simulator.core.presets.memoir.generated

import xyz.qwewqa.relive.simulator.core.stage.actor.StatData
import xyz.qwewqa.relive.simulator.core.stage.autoskill.EffectTag
import xyz.qwewqa.relive.simulator.core.stage.dress.ActParameters
import xyz.qwewqa.relive.simulator.core.stage.memoir.CutinBlueprint
import xyz.qwewqa.relive.simulator.core.stage.memoir.PartialMemoirBlueprint

val equip3000034 = PartialMemoirBlueprint(
  id = 3000034,
  name = "ウィッチ・ツインズ結成！",
  rarity = 3,
  baseStats = StatData(
    hp = 0,
    actPower = 6,
    normalDefense = 12,
    specialDefense = 0,
  ),
  growthStats = StatData(
    hp = 0,
    actPower = 821,
    normalDefense = 1643,
    specialDefense = 0,
  ),
  additionalTags = listOf(EffectTag.Aruru, EffectTag.Lalafin)
)
