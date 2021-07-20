import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdsByWordComponent } from './ads-by-word.component';

describe('AdsByWordComponent', () => {
  let component: AdsByWordComponent;
  let fixture: ComponentFixture<AdsByWordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdsByWordComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdsByWordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
