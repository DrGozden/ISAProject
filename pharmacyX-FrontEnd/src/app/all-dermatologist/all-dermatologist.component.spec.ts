import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllDermatologistComponent } from './all-dermatologist.component';

describe('AllDermatologistComponent', () => {
  let component: AllDermatologistComponent;
  let fixture: ComponentFixture<AllDermatologistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllDermatologistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllDermatologistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
