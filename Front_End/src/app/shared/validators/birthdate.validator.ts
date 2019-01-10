import {FormGroup} from '@angular/forms';

export function ValidateBirthdate(group: FormGroup) {
  const dayMili = 1000 * 60 * 60 * 24;
  const isBorn = group.value.isBorn;
  const userDate = (new Date(group.value.date)).getTime();
  const currentDate = new Date().getTime();

  if(isBorn && currentDate < userDate || !isBorn && currentDate > (userDate + dayMili)) {
    return {
      valid: false
    };
  }

  return null;
}
