import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayPinComponent } from './display-pin.component';

describe('DisplayPinComponent', () => {
  let component: DisplayPinComponent;
  let fixture: ComponentFixture<DisplayPinComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayPinComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayPinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
