export class DilemmaModel {

  id: number;
  weekNr: number;
  theme: string;
  feedback: string;
  periode: string;

  constructor(id: number, weekNr: number, theme: string, feedback: string, periode: string) {
    this.id = id;
    this.weekNr = weekNr;
    this.theme = theme;
    this.feedback = feedback;
    this.periode = periode;
  }

}
