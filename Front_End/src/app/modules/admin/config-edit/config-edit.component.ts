import {Component, OnInit} from '@angular/core';
import {ConfigService} from './config.service';
import {ConfigModel} from '../../../shared/models/config.model';

@Component({
  selector: 'app-config-edit',
  templateUrl: './config-edit.component.html',
  styleUrls: ['./config-edit.component.scss']
})
export class ConfigEditComponent implements OnInit {

  public saveClass = 'primary';
  public saveText = 'OPSLAAN';

  constructor(private configService: ConfigService) {
  }

  public weekDay: ConfigModel = new ConfigModel('MAIL_WEEK_DAY', '1');
  public dayTime: ConfigModel = new ConfigModel('MAIL_DAY_TIME', '00:00');
  public reminder: ConfigModel = new ConfigModel('MAIL_REMINDER', '1');

  ngOnInit() {
    this.weekDay = new ConfigModel('MAIL_WEEK_DAY', '1');
    this.dayTime = new ConfigModel('MAIL_DAY_TIME', '00:00');
    this.reminder = new ConfigModel('MAIL_REMINDER', '1');

    this.configService.getConfig().subscribe((data: ConfigModel[]) => {
      this.weekDay = data[0];
      this.dayTime = data[1];
      this.reminder = data[2];
    });
  }

  public update(): void {
    this.saveClass = 'success';
    this.saveText = 'OPGESLAGEN';

    setTimeout(() => {
      this.saveClass = 'primary';
      this.saveText = 'OPSLAAN';
    }, 1500);

    this.configService.updateConfig(this.weekDay, this.dayTime, this.reminder).subscribe(data => {
    }, error => {
    }, () => {
    });
  }
}
