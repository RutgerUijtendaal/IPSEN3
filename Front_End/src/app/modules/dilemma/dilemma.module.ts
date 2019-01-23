import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DilemmaRoutingModule } from './dilemma-routing.module';
import { DilemmaViewComponent } from './dilemma-view/dilemma-view.component';
import { HttpClientModule } from '@angular/common/http';
import { DilemmaAnswerComponent } from './dilemma-view/dilemma-answer/dilemma-answer.component';
import { ButtonsModule, ModalModule, PopoverModule, TooltipModule, WavesModule } from 'angular-bootstrap-md';
import { DilemmaLoadingComponent } from './dilemma-view/dilemma-loading/dilemma-loading.component';
import { AnswerVerifyComponent } from './dilemma-view/answer-verify/answer-verify.component';

// @ts-ignore
@NgModule({
  declarations: [DilemmaViewComponent, DilemmaAnswerComponent, DilemmaLoadingComponent, AnswerVerifyComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    DilemmaRoutingModule,
    ModalModule, TooltipModule, PopoverModule, ButtonsModule
  ]
})
export class DilemmaModule {
}
