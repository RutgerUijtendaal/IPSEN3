export class AnswerModel {

  id: number;
  dilemmaId: number;
  extension: string;
  text: string;

  constructor(id: number, dilemmaId: number, extension: string, text: string) {
    this.id = id;
    this.dilemmaId = dilemmaId;
    this.extension = extension;
    this.text = text;
  }

}
