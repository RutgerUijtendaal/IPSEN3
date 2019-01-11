export class DilemmaModel {

  id: number;
  weekNr: number;
  theme: string;
  feedback: string;

  constructor(id: number, weekNr: number, theme: string, feedback: string) {
    this.id = id;
    this.weekNr = weekNr;
    this.theme = theme;
    this.feedback = feedback;
  }

}
