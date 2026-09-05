# D3 Demo · 2026-08-29

工程：`knowledge/Android/demos/fragment/fg-d3-fragment-transaction/`

Logcat 滤 `HOST`：

- `replace·commit`：立刻 find 仍是旧隔断，随后旧的 `onDestroyView` + `onDestroy`
- `replace·commitNow`：先走完生命周期，立刻 find 已是新隔断
- `hide/show`：第二次只有 `onHiddenChanged`，没有 Home `onDestroy`；hide 不等于 `onPause`，被藏着的仍可能 `onResume`
- `add Extra`：Extra 盖上来，Home 不 Destroy
