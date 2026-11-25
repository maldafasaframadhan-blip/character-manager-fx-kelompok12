# CONTRACT — Character Manager v0.7

## Pembagian peran
- Model Manager: feature/model
- View Manager: feature/view
- Controller Manager: feature/controller

## Kontrak Model
- Character(String name, CharacterType type, int level)
- CharacterManager.addCharacter(Character)
- CharacterManager.getCharacters()
- CharacterManager.getAverageLevel()

## Kontrak View
- nameField
- typeChoiceBox
- levelField
- saveButton -> #handleSave
- resetButton -> #handleReset
- characterCardArea
- statusBar
- averageLevelLabel

## Kontrak Controller
- setManager(CharacterManager manager)
- initialize()
- handleSave()
- handleReset()
- refreshList()

## Aturan Git
- Tidak commit langsung ke main.
- Minimal 5 commit per anggota.
- Minimal 2 Pull Request per anggota.
- Merge setelah 1 approval.
