# Welcome to the contributing guide!
Thank you for taking an interest in this project and being motivated to contribute to it yourself. :) Together we can extend OWASP's work and strive to give developers a 
broad implementation coverage of the vulnerabilities discussed by OWASP MASWE. To do so in a standardized, clean fashion, it is important that all contributions follow
the same rules.

## How to get started
1. Fork and pull this repository.
2. The minimum and target SDK of this project is 35, meaning that Android 15 is needed to run the app.
3. Install AndroidStudio and fitting SDK.
4. Open the apps source code as a project in AndroidStudio.
5. Consult the Repo's [checklist](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/MAS_checklist.md) to see which MASWE vulnerabilities have already
   been implemented, which are being worked on and which are yet waiting for any attention.

## Writing style guidelines
- When contributing, please follow the principles and rules laid out in our [coding style guide](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/coding_standards.md).
- Consult the [README-template](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/blob/main/VulnerabilityReadmeTemplate.md) and [existing vulnerabilitsy-README's](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/tree/main/apps/maswe_platform/src/main/java/com/dkronig/maswe_platform/maswe_0053) when documenting newly implemented vulnerabilities for uniform formatting and language.


## App templates
- Currently, the vulnerabilities are grouped in apps by vulnerability categories, currently existing apps being maswe_storage, maswe_crypto and maswe_platforn.
- Every vulnerability is its own standalone unit, consisting of (at least) a MainActivity, a register, login and profile activity.
- For all of those, there are abstract template java classes in the [common app](https://github.com/domi-cmd/Reference_Application_for_OWASP_MASTG/tree/main/apps/common/src/main/java/com/dkronig/common).
- Take a look at existing apps and implemented vulnerabilities to get a feel for how they should look like.

## Issues
For creating new issues, please use the existing templates.

## Pull Request
- Once you have a vulnerability implemented that you want to contribute, open a pull request.
- After reviewing and accepting your pull request, the changes will be merged into the Repository.
- Once merged, your contributions will be visible here in this project!

## Thank you!🎉
