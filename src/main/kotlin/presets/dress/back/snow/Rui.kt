package xyz.qwewqa.relive.simulator.presets.dress.back.snow

import xyz.qwewqa.relivesim.stage.character.Character
import xyz.qwewqa.relivesim.stage.character.DamageType
import xyz.qwewqa.relivesim.stage.character.Position
import xyz.qwewqa.relive.simulator.presets.condition.IceOnlyCondition
import xyz.qwewqa.relive.simulator.presets.condition.SpecialDamageOnlyCondition
import xyz.qwewqa.relive.simulator.presets.condition.characterCondition
import xyz.qwewqa.relive.simulator.stage.Stage
import xyz.qwewqa.relive.simulator.stage.actor.ActType
import xyz.qwewqa.relive.simulator.stage.actor.Attribute
import xyz.qwewqa.relive.simulator.stage.actor.actsOf
import xyz.qwewqa.relive.simulator.stage.actor.defaultDressStats
import xyz.qwewqa.relive.simulator.stage.autoskill.new
import xyz.qwewqa.relive.simulator.stage.buff.*
import xyz.qwewqa.relive.simulator.stage.loadout.Dress
import xyz.qwewqa.relive.simulator.stage.passive.*

val StageGirlRui = Dress(
    name = "Stage Girl Rui",
    character = Character.Tamao,
    attribute = Attribute.Ice,
    damageType = DamageType.Normal,
    position = Position.Back,
    stats = defaultDressStats.copy(
        hp = 33766,
        actPower = 3194,
        normalDefense = 1740,
        specialDefense = 1190,
        agility = 2113,
    ),
    acts = actsOf(
        ActType.Act1("Ardent Slash", 2) {
            targetBack().act {
                attack(
                    modifier = 176,
                    hitCount = 1,
                )
            }
        },
        ActType.Act2("Resolute Blade", 2) {
            targetBack().act {
                attack(
                    modifier = 187,
                    hitCount = 2,
                    bonusMultiplier = 150,
                    bonusCondition = SpecialDamageOnlyCondition,
                )
                applyBuff(
                    ActPowerDownBuff,
                    value = 30,
                    turns = 3,
                )
                applyBuff(
                    NormalDefenseDownBuff,
                    value = 30,
                    turns = 3,
                )
            }
        },
        ActType.Act3("Strike of Determination", 2) {
            targetBack().act {
                attack(
                    modifier = 198,
                    hitCount = 1,
                )
            }
            targetAllyAoe().act {
                applyBuff(
                    DexterityUpBuff,
                    value = 30,
                    turns = 3,
                )
                applyBuff(
                    ApDownBuff,
                    turns = 2,
                )
            }
        },
        ActType.ClimaxAct("My life is on this stage!", 2) {
            targetAoe().act {
                dispelTimed(BuffCategory.Positive)
            }
            targetAnyRandom(10).act {
                attack(
                    modifier = 450,
                    hitCount = 10,
                )

                applyBuff(
                    StopBuff,
                    chance = 33,
                )
            }
        }
    ),
    autoSkills = listOf(
        GenericBuffPassive(EffectiveDamageDealtBuff) { targetAllyAoe() }.new(20, turns = 2),
        GenericBuffPassive(CriticalUpBuff) { targetAllyAoe() }.new(20, turns = 2),
        TeamBrillianceUpPassive.new(20) + characterCondition(Character.Tamao),
    ),
    unitSkill = listOf(
        TeamActPassive.new(50) + IceOnlyCondition,
        TeamCriticalPassive.new(50) + IceOnlyCondition,
    )
)

val StageGirlRui95 = StageGirlRui.copy(
    stats = StageGirlRui.stats.copy(
        hp = 32492,
        normalDefense = 1677,
        specialDefense = 1147,
    )
)