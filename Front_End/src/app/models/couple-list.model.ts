export class CoupleListModel {

  firstName1: string;
  phoneNr1: string;
  email1: string;

  firstName2: string;
  phoneNr2: string;
  email2: string;

  isBorn: boolean;
  date: number;

  constructor(firstName1: string, phoneNr1: string, email1: string,
              firstName2: string, phoneNr2: string, email2: string,
              isBorn: boolean, date: number) {
    this.firstName1 = firstName1;
    this.phoneNr1 = phoneNr1;
    this.email1 = email1;
    this.firstName2 = firstName2;
    this.phoneNr2 = phoneNr2;
    this.email2 = email2;
    this.isBorn = isBorn;
    this.date = date;
  }

}
