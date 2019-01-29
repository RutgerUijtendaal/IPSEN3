import {FormControl} from '@angular/forms';

export function ValidatePhone(control: FormControl) {
  const PHONE_REGEX = new RegExp('^\\+\\d{11}$');
  const phone = control.value;

  return PHONE_REGEX.test(phone) ? null : {
    validatePhone: {
      valid: false
    }
  };
}
