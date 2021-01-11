import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamReservationsComponent } from './exam-reservations.component';

describe('ExamReservationsComponent', () => {
  let component: ExamReservationsComponent;
  let fixture: ComponentFixture<ExamReservationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExamReservationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
