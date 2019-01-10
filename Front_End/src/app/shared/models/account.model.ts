export class AccountModel {

  public type: string;
  public name: string;
  public email: string;
  public password: string;

  constructor() {
  }

  setData (data) {
    let loginData;
    loginData = data as AccountModel;
    this.type = loginData.type;
    this.name = loginData.name;
    this.email = loginData.email;
    this.password = loginData.password;
    console.log(this.name);
  }

}
