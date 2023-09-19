import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerpanelComponent } from './customerpanel.component';

describe('CustomerpanelComponent', () => {
  let component: CustomerpanelComponent;
  let fixture: ComponentFixture<CustomerpanelComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomerpanelComponent]
    });
    fixture = TestBed.createComponent(CustomerpanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
