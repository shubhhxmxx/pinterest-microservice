import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePinComponent } from './create-pin.component';

describe('CreatePinComponent', () => {
  let component: CreatePinComponent;
  let fixture: ComponentFixture<CreatePinComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatePinComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatePinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
