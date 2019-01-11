export class AnswerModel {

  id: number;
  dilemmaId: number;
  url: string;
  text: string;

  constructor(id: number, dilemmaId: number, url: string, text: string) {
    this.id = id;
    this.dilemmaId = dilemmaId;
    this.url = url;
    this.text = text;
  }

}
