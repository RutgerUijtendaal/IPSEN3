import {Directive, ElementRef, HostListener, Input, Renderer2} from '@angular/core';

@Directive({
  selector: '[appDropdownNav]'
})
export class DropdownNavDirective {

  constructor(private renderer: Renderer2) {

  }

  private isOpen = true;

  @Input('appDropdownNav') element: ElementRef;

  @HostListener('click') toggleOpen() {
    this.isOpen = !this.isOpen;
    if (this.isOpen) {
      this.renderer.addClass(this.element, 'collapse');
    } else {
      this.renderer.removeClass(this.element, 'collapse');
    }
  }

}
