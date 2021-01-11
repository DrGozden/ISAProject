import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultingReservationsComponent } from './consulting-reservations.component';

describe('ConsultingReservationsComponent', () => {
  let component: ConsultingReservationsComponent;
  let fixture: ComponentFixture<ConsultingReservationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultingReservationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultingReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
