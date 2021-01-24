import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinalConsultationReservationComponent } from './final-consultation-reservation.component';

describe('FinalConsultationReservationComponent', () => {
  let component: FinalConsultationReservationComponent;
  let fixture: ComponentFixture<FinalConsultationReservationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinalConsultationReservationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinalConsultationReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
