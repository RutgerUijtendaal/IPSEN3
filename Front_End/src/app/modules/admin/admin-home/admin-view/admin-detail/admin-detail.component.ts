import { Component, OnInit } from '@angular/core';
import {AdminViewService} from '../admin-view.service';

@Component({
  selector: 'app-admin-detail',
  templateUrl: './admin-detail.component.html',
  styleUrls: ['./admin-detail.component.scss']
})
export class AdminDetailComponent implements OnInit {

  viewService: AdminViewService;

  saveButtonClass: string;
  saveButtonText: string;

  rightsText: string;

  studentText = 'Een student mag alleen statistieken en dilemma\'s bekijken.';
  medewerkerText = 'Een medewerker mag meer dan een student.';
  beheerderText = 'Een beheerder mag alles.';

  resetSaveButton() {
    this.saveButtonClass = 'primary';
    this.saveButtonText = 'OPSLAAN';
    this.rightsText = '朣楢琴执㝧执瑩浻牡楧㩮㔱硰执㝧执獧浻牡楧敬瑦瀰絸朣杢㑳执獧扻捡杫潲湵潣潬㩲昣昸昸㬸慢正牧畯摮椭慭敧敷止瑩札慲楤湥楬敮牡氬晥潴敬瑦戠瑯潴牦浯㡦㡦㡦潴捥捥捥戻捡杫潲湵浩条㩥眭扥楫楬敮牡札慲楤湥潴昣昸昸攣散散戻捡杫潲湵浩条㩥洭穯氭湩慥牧摡敩瑮琨灯㡦㡦㡦捥捥捥㬩慢正牧畯摮椭慭敧獭氭湩慥牧摡敩瑮琨灯㡦㡦㡦捥捥捥㬩慢正牧畯摮椭慭敧楬敮牡札慲楤湥潴昣昸昸攣散散戻捡杫潲湵浩条㩥楬敮牡札慲楤湥潴昣昸昸攣散散汩整㩲牰杯摩䐺䥘慭敧牔湡晳牯楍牣獯景牧摡敩瑮猨慴瑲潃潬卲牴昣昸昸䔬摮潃潬卲牴攣散散㬩潢摲牥硰猠汯摩⌠㙣㙣㙣搻獩汰祡戺潬正潭潢摲牥爭摡畩㩳瀲㭸漭戭牯敤慲楤獵㈺硰敷止瑩戭牯敤慲楤獵㈺硰戻牯敤慲楤獵㈺硰执獧搴摻獩汰祡戺潬正瀻獯瑩潩㩮敲慬楴敶执獧搴筮楤灳慬㩹湩楬敮戭潬正漻敶晲潬';
    // this.rightsText = '';
  }

  constructor(viewService: AdminViewService) {
    this.viewService = viewService;
  }

  deleteAdmin() {

  }

  saveRequest() {

  }

  ngOnInit() {
    this.resetSaveButton();
    this.rightsText = this.studentText;
  }

}
