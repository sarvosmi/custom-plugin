# my-custom-plugin

plugin for demo

## Install

```bash
npm install my-custom-plugin
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`testPluginMethod(...)`](#testpluginmethod)
* [`pluginPermissionMethod()`](#pluginpermissionmethod)
* [`openWebsite(...)`](#openwebsite)
* [`calenderTest()`](#calendertest)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### testPluginMethod(...)

```typescript
testPluginMethod(options: { msg: string; }) => Promise<{ value: string; }>
```

| Param         | Type                          |
| ------------- | ----------------------------- |
| **`options`** | <code>{ msg: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### pluginPermissionMethod()

```typescript
pluginPermissionMethod() => Promise<void>
```

--------------------


### openWebsite(...)

```typescript
openWebsite(options: { siteURL: string; }) => Promise<void>
```

| Param         | Type                              |
| ------------- | --------------------------------- |
| **`options`** | <code>{ siteURL: string; }</code> |

--------------------


### calenderTest()

```typescript
calenderTest() => Promise<void>
```

--------------------

</docgen-api>
