import { Injectable } from '@angular/core';

export interface Menu {
  state: string;
  name: string;
  type: string;
  icon: string;
}

// https://fonts.google.com/icons?selected=Material+Symbols+Outlined:component_exchange:FILL@0;wght@400;GRAD@0;opsz@24
const MENUITEMS = [
  { state: 'dashboard', name: 'Dashboard', type: 'link', icon: 'av_timer' },
  { state: 'category', type: 'link', name: 'Category', icon: 'tab' },
  { state: 'player', type: 'link', name: 'Players', icon: 'person_add' },
  { state: 'soccer-games-list', type: 'link', name: 'Soccer Games List', icon: 'sports_soccer' },
  { state: 'soccer-games', type: 'link', name: 'Soccer Games', icon: 'sports_soccer' },
  { state: 'button', type: 'link', name: 'Buttons', icon: 'crop_7_7' },
  { state: 'grid', type: 'link', name: 'Grid List', icon: 'view_comfy' },
  { state: 'lists', type: 'link', name: 'Lists', icon: 'view_list' },
  { state: 'menu', type: 'link', name: 'Menu', icon: 'view_headline' },
  { state: 'tabs', type: 'link', name: 'Tabs', icon: 'tab' },
  { state: 'stepper', type: 'link', name: 'Stepper', icon: 'web' },
  {
    state: 'expansion',
    type: 'link',
    name: 'Expansion Panel',
    icon: 'vertical_align_center'
  },
  { state: 'chips', type: 'link', name: 'Chips', icon: 'vignette' },
  { state: 'toolbar', type: 'link', name: 'Toolbar', icon: 'voicemail' },
  {
    state: 'progress-snipper',
    type: 'link',
    name: 'Progress snipper',
    icon: 'border_horizontal'
  },
  {
    state: 'progress',
    type: 'link',
    name: 'Progress Bar',
    icon: 'blur_circular'
  },
  {
    state: 'dialog',
    type: 'link',
    name: 'Dialog',
    icon: 'assignment_turned_in'
  },
  { state: 'tooltip', type: 'link', name: 'Tooltip', icon: 'assistant' },
  { state: 'snackbar', type: 'link', name: 'Snackbar', icon: 'adb' },
  { state: 'slider', type: 'link', name: 'Slider', icon: 'developer_mode' },
  {
    state: 'slide-toggle',
    type: 'link',
    name: 'Slide Toggle',
    icon: 'all_inclusive'
  }
];

@Injectable()
export class MenuItems {
  getMenuitem(): Menu[] {
    return MENUITEMS;
  }
}
