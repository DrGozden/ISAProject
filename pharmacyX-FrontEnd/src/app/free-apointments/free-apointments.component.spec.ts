import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FreeApointmentsComponent } from './free-apointments.component';

describe('FreeApointmentsComponent', () => {
  let component: FreeApointmentsComponent;
  let fixture: ComponentFixture<FreeApointmentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FreeApointmentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FreeApointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
