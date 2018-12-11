import {FormControl} from '@angular/forms';

export function ValidatePhone(control: FormControl) {
  let PHONE_REGEX = new RegExp('^\\+\\d{11}$');
  let phone = control.value;

  return PHONE_REGEX.test(phone) ? null : {
    validatePhone: {
      valid: false
    }
  }
}
