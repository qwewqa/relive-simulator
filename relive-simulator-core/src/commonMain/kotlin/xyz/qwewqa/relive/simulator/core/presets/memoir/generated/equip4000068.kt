package xyz.qwewqa.relive.simulator.core.presets.memoir.generated

import xyz.qwewqa.relive.simulator.core.stage.actor.StatData
import xyz.qwewqa.relive.simulator.core.stage.autoskill.EffectTag
import xyz.qwewqa.relive.simulator.core.stage.dress.ActParameters
import xyz.qwewqa.relive.simulator.core.stage.memoir.CutinBlueprint
import xyz.qwewqa.relive.simulator.core.stage.memoir.PartialMemoirBlueprint

val equip4000068 = PartialMemoirBlueprint(
  id = 4000068,
  name = "星に願いが届いた日",
  rarity = 4,
  baseStats = StatData(
    hp = 210,
    actPower = 0,
    normalDefense = 18,
    specialDefense = 0,
  ),
  growthStats = StatData(
    hp = 23924,
    actPower = 0,
    normalDefense = 2050,
    specialDefense = 0,
  ),
  additionalTags = listOf(EffectTag.Michiru, EffectTag.MeiFan)
)
