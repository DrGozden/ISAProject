import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinalDrugReservationComponent } from './final-drug-reservation.component';

describe('FinalDrugReservationComponent', () => {
  let component: FinalDrugReservationComponent;
  let fixture: ComponentFixture<FinalDrugReservationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinalDrugReservationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinalDrugReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
