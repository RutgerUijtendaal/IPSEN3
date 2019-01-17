export class ResultModel {

  public id: number;
  public parentId: number;
  public answerId: number;
  public answeredTime: Date;
  public sentTime: Date;


  constructor(id: number, parentId: number, answerId: number, answeredTime: string, sentTime: string) {
    this.id = id;
    this.parentId = parentId;
    this.answerId = answerId;
    this.answeredTime = new Date(+answeredTime);
    this.sentTime = new Date(+sentTime);
  }


}
