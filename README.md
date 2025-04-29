> [!WARNING]
> Monomial is in development. Information provided here or through documentation may be inaccurate or outdated.
> Code is also not guaranteed any backwards compatibility until 1.0.

# Monomial
Monomial is a new framework for modding Minecraft servers. It has a few core philosophies in mind:
- All code should be portable across as many environments & versions of Minecraft as possible.
- Code should feel like you're writing new Vanilla code, with the utilities of writing plugin code.

# Providers
Monomial should be implemented through a provider. This is a mod, plugin, or some kind of modification to the server
based on an underlying modding framework. While a pure Monomial server is theoretically possible by patching the Minecraft
server jar, this approach is cumbersome and requires advanced build tooling and de-compilation of Minecraft. This also 
would break compatibility with the rest of the Minecraft development ecosystem, which is not the goal.

# Common Patterns
## Lifecycles
A big component of Monomial is the `Lifecycle` annotation. All methods and classes will have this annotation. It is a 
way of communicating what MC and API versions an item will be supported from and deprecated in.

For example:
```java
@Lifecycle(platforms = {"java"}, since = "1.0")
public static final EntityType TEXT_DISPLAY = placeholder();
```
This lifecycle means this API is valid since API version 1.0, and only works on Java Edition-based platforms.