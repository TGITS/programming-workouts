export const abilityModifier = (ability) => {
  if (ability < 3) {
    throw new Error('Ability scores must be at least 3');
  }

  if (ability > 18) {
    throw new Error('Ability scores can be at most 18');
  }

  return Math.floor((ability - 10) / 2)
};

function rollSixSidedDice() {
  return 1 + Math.floor(Math.random() * 6);
}

export class Character {
  static rollAbility() {
    return Array.of(rollSixSidedDice(), rollSixSidedDice(), rollSixSidedDice(), rollSixSidedDice()).sort().slice(1).reduce((a, b) => a + b, 0)
  }

  constructor() {
    this._strength = Character.rollAbility();
    this._dexterity = Character.rollAbility();
    this._constitution = Character.rollAbility();
    this._intelligence = Character.rollAbility();
    this._wisdom = Character.rollAbility();
    this._charisma = Character.rollAbility();
  }

  get strength() {
    return this._strength;
  }

  get dexterity() {
    return this._dexterity;
  }

  get constitution() {
    return this._constitution;
  }

  get intelligence() {
    return this._intelligence;
  }

  get wisdom() {
    return this._wisdom;
  }

  get charisma() {
    return this._charisma;
  }

  get hitpoints() {
    return 10 + abilityModifier(this.constitution);
  }
}
