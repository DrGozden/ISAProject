import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamReservationComponent } from './exam-reservation.component';

describe('ExamReservationComponent', () => {
  let component: ExamReservationComponent;
  let fixture: ComponentFixture<ExamReservationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExamReservationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
