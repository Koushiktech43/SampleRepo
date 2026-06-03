# Security Guidelines

Security best practices for SampleTestApplication development.

## Network Security

- ✅ Enforce HTTPS only for all API calls
- ✅ Implement certificate pinning with OkHttp
- ✅ Validate SSL certificates
- ✅ Never hardcode API base URLs in code
- ✅ Use custom OkHttp interceptors for security headers

## Data Protection

### At Rest
- Encrypt sensitive data using Android Keystore
- Use EncryptedSharedPreferences for shared preferences
- Implement Room database encryption if needed

### In Transit
- Always use HTTPS with certificate pinning
- Validate all API responses
- Sanitize data before displaying

### What NOT to Store
- ❌ Passwords (use authentication tokens instead)
- ❌ API tokens in plain text (encrypted storage only)
- ❌ Personal identifiable information (PII) without encryption
- ❌ Payment card information

## Application Security

### Code Obfuscation
- Enable ProGuard/R8 for release builds
- Configure rules in `proguard-rules.pro`
- Test obfuscated builds thoroughly

### Logging
- ❌ Never log sensitive data (passwords, tokens, PII)
- ✅ Disable all logs in release builds
- Use BuildConfig.DEBUG to conditionally enable logs

### Screenshots & UI Security
- Prevent screenshots on sensitive screens
- Set FLAG_SECURE on sensitive activities
- Clear sensitive data from memory when app loses focus

### Root/Jailbreak Detection
- Implement basic device integrity checks
- Consider Google SafetyNet attestation
- Handle compromised devices gracefully

## Input Validation

- Validate all user inputs before processing
- Sanitize API responses for injection attacks
- Use type-safe data structures (sealed classes, data classes)
- Limit file upload sizes

## Dependencies

- Keep dependencies updated
- Monitor for security advisories
- Use version catalogs for centralized management
- Review dependency security regularly

## Testing

- Include security tests in test suite
- Test with invalid/malicious inputs
- Verify encryption is working
- Test on rooted devices

