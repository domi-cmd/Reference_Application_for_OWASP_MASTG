Here I'll collect my thoughts and notes on read papers

# Research question - What is OWASP (MASTG) used for in the academic world?

### 1 The OWApp Benchmark: an OWASP-compliant Vulnerable Android App Dataset
- Published 2024

Goal of paper:
1. Evaluate OWASP MASVS&MASTG, find security controls relevant to SAST tools that can be automated using static analysis techniques.
2. Review existing benchmark suites and datasets for Android apps to assess their coverage of OWASP security controls.
3. Propose their own benchmark suite "OWApp Benchmark Suite", includes dataset covering all MASVS security controls that are coverable using SAST techniques.
- Popular apps on average 39 security and privacy vulnerabilities
- SAST (Static Application Security Testing) & DAST (Dynamic Application Security Testing)
- State of the art datasets for SAST: Ghera, DroidBench
-> "these datasets **focus on specific categories of vulnerabilities** and **fail to encompass the full range of security controls outlined by standards such as the OWASP MASVS**. For example, while Ghera offers vulnerable apps based on common coding errors, it does not address more advanced security issues, such as complex cryptographic vulnerabilities or multicomponent interactions, which are critical in modern mobile app ecosystems"

___

### 2 [Security Assessment for Digital Wallet Payment Partner Applications using the OWASP Method: A Case Study in Indonesia](https://www.researchgate.net/profile/Muhammad-Saifulhakim/publication/378480579_Security_Assessment_for_Digital_Wallet_Payment_Partner_Applications_using_the_OWASP_Method_A_Case_Study_in_Indonesia/links/65dc4147adf2362b6357e589/Security-Assessment-for-Digital-Wallet-Payment-Partner-Applications-using-the-OWASP-Method-A-Case-Study-in-Indonesia.pdf)
file: [Security-Assessment-for-Digital-Wallet-Payment-Partner-Applications-using-the-OWASP-Method-A-Case-Study-in-Indonesia.pdf](https://github.com/user-attachments/files/23166032/Security-Assessment-for-Digital-Wallet-Payment-Partner-Applications-using-the-OWASP-Method-A-Case-Study-in-Indonesia.pdf)

#### General
- Published 2024
- OWASP areas: MASTG, MASVS, Risk rating methodology, Top 10 Mobile Risks

#### Goal of paper:
1. Security assessment of a digital wallet app in indonesia using the OWASP framework.
2. Data retrieval using the OWASP MASTG and MASVS
3. Use SAST & DAST for finding vulnerabilities, match them to OWASP vulnerabilities.
4. Use OWASP risk rating methodology to assess the found security risks.

#### Notes
- 63% android apps exhibit vulnerabilities, on average 39 per app.
- Gaming and financial apps are particularly vulnerable, financial transaction apps have 80% vulnerability rate.
- OWASP framework is well suited for security risk assessment.
- Insecure data storage stands out compared to other vulnerabilities with a vulnerabilit rating of up to 76%.
- 74% of all google play apps are susceptible to at least one of the OWASP top 10 risks.
- Multiple literature studies and articles show that OWASP presents a viable solution for security assessment.

#### Study result
- Of 84 MASVS verification points, 68 were labelled as "pass", 10 were deemed not applicable, 6 were failed (vulnerability found).
- 1 vulnerability found using SAST.
- 5 vulnerabilities found using DAST.
- To avoid "not applicable" results using the OWASP MASVS, Testers need to be able to access and view contents of confidential documentation files, or more secure
  access rights on the application system need to be implemented.

___

### 3 [The Application of Mobile Security Framework (MOBSF) and Mobile Application Security Testing Guide to Ensure the Security in Mobile Commerce Applications](https://www.jsisfotek.org/index.php/JSisfotek/article/view/231/153)
file: [document (1).pdf](https://github.com/user-attachments/files/23166027/document.1.pdf)

#### General
- Not published in renowned journal
- Fishy (repeating sentences, things that arent true "MOBSF encompasses various frameworks, including OWASP")
- Published 2023
- OWASP areas: MASTG, MASVS

#### Goal of paper:
1. Evaluate and identify security vulnerabilities in an android application using the MOBSF (Mobile Security Framework) and the OWASP MASTG.
2. Focus on data storage and Authentication

#### Notes
- 43% of Android applications have security vulnerabilities with high risk.
- Most of them due to "weakness in security mechanisms since apps creation" and "granting privileges that are inappropriately exploited by attackers".
- Increase in cybercrimes (in indonesia) due to carelessness and weak online technology security systems from mobile commerce applications who often ask for permission regarding personal data from their users. This data is used to support the application's business processes and stolen by cybercriminals.
- OWASP MASTG and MOBSF "furnish unambiguous security analysis outcomes, which can serve as a basis for security enhancements for Android app developers."
- The Mobile Security Framework (MobSF) is an automated testing framework for open-source mobile apps (Android, iOS, and Windows).
- MOBSF performs malware analysis and penetration testing and can identify vulnerabilities that attackers can exploit in mobile apps. In prior research, the MOBSF was employed for static analysis, which yielded a true positive of up to 97%.
- OWASP MASTG can find more vulnerabilities than when using the AndroBug framework!

#### Study result
- False positive from automated tests of MOBSF, when compared to MASTG & MASVS
- A vulnerability in the parameter determining whether sensitive data is sent to third parties was discovered during the exploitation stage based on the findings of the validation test with the OWASP MASTG framework.
- Apart from that, other security issues were also found in the testing best practices for passwords parameter, where hackers can freely guess passwords by carrying out brute force attacks because existing applications do not implement a strong password creation policy.

___

### 4 [Analysis of Fraud Attacks Using Android Package Kit in Indonesia](https://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber=10732435)
file: [Analysis_of_Fraud_Attacks_Using_Android_Package_Kit_in_Indonesia.pdf](https://github.com/user-attachments/files/23166023/Analysis_of_Fraud_Attacks_Using_Android_Package_Kit_in_Indonesia.pdf)

#### General
- Published 2024
- OWASP Areas: MASTG (5 Stage pen testing), Mapping vulnerabilities to OWASP Top 10 & MASVS

#### Goal of paper:
1. "This research carefully dissects the patterns and tactics used in these fraudulent attacks, explaining the common traits of malicious APKs and the strategies used by threat actors to exploit vulnerabilities in Android devices."
2. "OWASP MASTG was used specifically to test eight malicious applications that have been discovered and utilized by criminals to commit fraud in Indonesia."

#### Notes
- "This research underscores the importance of more robust security protocols, better user education, and strict regulatory frameworks to mitigate the risks associated with phishing APK attacks"
- "APK files, the primary packaging format for Android applications, have become the preferred means for criminals to perpetrate scams."
- Many APKs are malicious because they are:
1. In a debuggable state
2. have exported activities and broadcast receivers and
3. the reccurence of dangerous permissions, such as Android
- "conducting further analysis, these vulnerabilities can be mapped against several standards such as CWE, OWASP Top 10, and OWASP MASVS. All APK has been joined and analyzed with other standards"
- Further common exploitations are:
1. Malicious Code Injection: Embedding harmful code within seemingly benign APKs to execute unauthorized actions.
2. Abuse of Permissions: Malicious APKs often request excessive permissions that go beyond their functional needs, enabling them to access sensitive data.
3. Old OTP Verification Exploits: Utilizing outdated onetime password (OTP) methods to gain unauthorized access.

#### Study result
- "The findings suggest a need for continuous monitoring and updating of security protocols, increased public awareness of digital threats, and stronger collaboration between the governmental and private sectors in Indonesia to effectively combat these evolving cyber threats. Finally, this research provides valuable insights for forensic analysis to trace cybercriminal actors. These insights underscore the broader role of digital forensics in understanding and mitigating the impact of mobile phishing attacks in Indonesia"

___

### 5 [Evaluating Compliance of of the XYZ Ministry’s Android Messaging Applications with OWASP MASVS: A Comprehensive Case Study](https://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber=10859915)
file: [Evaluating_Compliance_of_of_the_XYZ_Ministrys_Android_Messaging_Applications_with_OWASP_MASVS_A_Comprehensive_Case_Study.pdf](https://github.com/user-attachments/files/23166017/Evaluating_Compliance_of_of_the_XYZ_Ministrys_Android_Messaging_Applications_with_OWASP_MASVS_A_Comprehensive_Case_Study.pdf)


#### General
- Published 2024
- OWASP areas:
1. Applied MASVS standard to app using manual and automated testing, evaluating the apps compliance to MASVS
2. Uses MASVS-NETWORK-2 as focus of study
3. Uses OWASP Top 10 Mobile Risks to help decide on focus of study
4. Use MASTG to test whether the app adheres to the requirements set by the MASVS standard.
5. Use the MAS checklist to re-examine the evaluation results from the MASTG tests.

#### Goal of paper:
1. Evaluating the compliance of the gov messaging app based on MASVS, using MASTG to test and MAS to verify
2. identifying common vulnerabilities and compliance gaps
3. providing actionable recommendations for improving the application's security posture

#### Notes
- study does a case analysis that assesses the compliance of the XYZ Ministry's Android messaging app with MASVS
- using automated and manual testing approaches
- concentrated on MASVS-NETWORK-2 security controls
- insecure communication ranks third in the OWASP Top 10 Mobile Risks 2016
- 38% of mobile applications are vulnerable to insecure communication
- MASVS sets the security standards, MSTG provides the testing techniques, and MAS serves as a checklist to ensure all relevant tests are conducted and aligned with MASVS requirements
- OWASP MASVS was selected as the primary standard for evaluation due to its comprehensive nature and alignment with mobile application security requirements.
- the tested application failed to meet two of the five test sections in MASVS-NETWORK-2.

#### Study result
- the tested application failed to meet two of the five test sections in MASVS-NETWORK-2.
- Study recommends to change Network Security Configs as to only accept certificates from the system and not from the user as well.


### 6 Penetration Testing with OWASP Mobile for Android Security Optimization
(Only Download link available, see file [jochac-deco-ok.pdf](https://github.com/user-attachments/files/23165785/jochac-deco-ok.pdf)

#### General
- Not always clear what is meant by the wording of the study, not the best english translation?
- Published 2023
- OWASP Areas:
1. MASTG 5 Steps guide to penetration testing

#### Goal of paper:
1. Carry out a pentesting attack following the MASTG 5 steps laid out for this.
2. Describe the steps taken to do so
3. Conclude with the information maliciously obtained, as to allow security parties to patch the respective loopholes and exploits.

#### Notes
- Pure OWASP focus on the 5 MASTG steps described to carry out penetration testing

#### Study result
- Study authors managed to extract data on the applications IOC's aswell as contact and SMS data, and audio records.
- Study shows that the MASTG 5 steps pentesting "guide" is a sensible approach to pentest applications, as well as do so in an academic context.

### 7 [Evaluating Mobile Banking Application Security Posture Using the OWASP’s MASVS Framework](https://dl.acm.org/doi/abs/10.1145/3588001.3609367)
file: [3588001.3609367.pdf](https://github.com/user-attachments/files/23166012/3588001.3609367.pdf)


#### General
- Published 2023, Ruanda
- OWASP Areas:
1. OWASP MASVS & MASTG, using their guidelines checks and tests to evaluate their chosen batch of fintech applications.

#### Goal of paper:
1. Choose 18 apps belonging to different africa financial institutions, evaluate them for vulnerabilities using OWASP MASVS and MASTG
2. Document the found vulnerabilities
3. Give recommendations on fixing the vulnerabilities
4. Conduct survey to find out cause for the found vulnerabilities (lack of funding, lack of knowledge etc.)
5. Conclude with statement about importance of proper security with MASVS

#### Notes
- MASVS chosen as testing framework, since it is specifically designed to "be a guide and checklist for evaluating the security of mobile applications".
- Strictest level of MASVS, L2, was applied
- authors have chosen to focus solely on unauthenticated client-side testing, following the black-box testing approach. Did not implement all MASVS tests.

#### Study result
- Worst outcomes:
1. All applications allowed for screenshots of sensitive information
2. 81% of all apps did not enforce in-app updates.
3. 78% of apps used outdated and thus insecure cryptographic protocols such as MD5, SHA-1 and DES
4. 10/18 apps use security libraries that are outdated and known to have vulnerabilities.
5. 8/18 were using insecure random number generation for their cryptographic protocols
6. 5/18 allowed cleartext traffic to their endpoints
7. 83% did not enforce proper runtime buffer overflow protection
8. 33% of apps did not detect running on an emulator, neither running on a rooted/jailbroken device
9. 50% did not detect an attached debugger
10. 55% had no code obfuscation in place, none of the apps had any other obfuscation, such as anti-debug obfuscation in place.

- Survey results:
1. 78% of respondents said they avoided implementing security due to a lack of expertise and knowledge about security.
2. 63% thought that implementing security measures in applications is too complex or/and time-consuming.

- The authors conclude that its better to ensure security by using MASVS instead of OWASP top 10 risks, since "this approach will yield better results and completeness than simply relying on general web application vulnerabilities such as the OWASP Top 10."


### 8 [Evaluating Penetration Testing Methodologies Formobile Applications: A Comparative Study of Android and Ios Security](https://papers.ssrn.com/sol3/papers.cfm?abstract_id=5384360)

#### General
- Published AUgust 2025 (on SSRN)
- OWASP Areas:
1. Discusses the significant gap between currently used pen-testing tools and the recommended, proactive and secure-by-design approach as defined by OWASP MASVS and MASTG.

#### Goal of paper:
1. This literature review critically examines penetration testing methodologies applied to mobile applications on Android and iOS platforms, with particular attention to how platform-specific architectures and constraints shape testing practices, tool effectiveness, and security assessment outcomes.

#### Notes
- there remains a significant gap between these recommended practices by OWASP (MASVS & MASTG) and the capabilities of commonly used penetration testing tools.
- OWASP is emphasising the need to move beyond automated scanning to include manual testing of complex vulnerabilities, such as those related to authentication, data storage, and inter-process communication
- This disconnect suggests that current industry tools are misaligned with the proactive security posture promoted by MASTG and MASVS.

### 9 [Security Analysis of the Lombok Tourism Android Application Using Penetration Testing (Pentesting) Methods Based on the OWASP Mobile Top 10-2024 Framework](https://eprints.unram.ac.id/48862/2/Jurnal_Ida%20Bagus%20Adi%20Surya%20Kemenuh%20%281%29.pdf)
file: [Jurnal_Ida Bagus Adi Surya Kemenuh (1).pdf](https://github.com/user-attachments/files/23166008/Jurnal_Ida.Bagus.Adi.Surya.Kemenuh.1.pdf)

#### General
- Published 2024? (Direct filedownload, no publisher?)
- OWASP Areas:
1. Used the OWASP Top 10 2024 to identify vulnerabilities to pen-test the tourism app on

#### Goal of paper:
1. 

#### Notes
-

#### Study result
-

### 10 [Mutation Testing to Support the Security Testing of Android Applications](https://sol.sbc.org.br/index.php/sast/article/view/30213)
file: [30213-1418-24654-1-10-20240916.pdf](https://github.com/user-attachments/files/23166064/30213-1418-24654-1-10-20240916.pdf)

#### General
- Published 
- OWASP Areas:
1. (Considered using OWASP Top 10 as knowledge base for android vulnerabilities, went with Google's Developers Common Risks instead)
2. Used 10 intentionally vulnerable MASTG reference apps to test their mutation operators on.

#### Goal of paper:
1. Use mutation testing for android app security testing
2. Identify common android vulnerabilities
3. Propose new mutation operators based on these vulnerabilities
4. Develop mutation tool to support mutant generation
5. Use this tool on 10 MASTG vulnerable reference apps
6. Use MobSFScan as a static analysis tool to try and detect the mutants

#### Notes
- Very good explanations and code snippets on the following vulnerabilities:
1. Improper export of app components
2. Debuggable app
3. Implicit pending intent
4. Hardcoded secrets
5. Tapjacking attacks
6. Plaintext HTTP
- Study shows that mobsfscan is not adequate for thorough vulnerability/security analysis of apps:
1. Was not able to detect any of the 23 Improper Export mutants
2. Even failed to detect an already existing, known improper export vulnerability in one of the used MASTG apps
3. "Nevertheless, due to the semantic nature of this vulnerability and the limitations of our tests, it was impossible to detect improper export mutations as vulnerabilities. Doing so would require different kinds of tests, perhaps involving human analysts"
4. In 4/10 apps mobsfscan failed to detect any of the hardcoded secret vulnerabilities, despite them having the same pattern as detected hardcoded secret vulnerabilities.
5. **"The only reasonable explanation we could think of for this result is that there must be a design flaw in mobsfscan which leads to it systematically failing to find hardcoded secrets in some cases."**

#### Study result
-

### 11 [Evaluation of Web Vulnerability Scanners Based on OWASP Benchmark](https://ieeexplore.ieee.org/abstract/document/8638176)
file: [Evaluation_of_Web_Vulnerability_Scanners_Based_on_OWASP_Benchmark.pdf](https://github.com/user-attachments/files/23166180/Evaluation_of_Web_Vulnerability_Scanners_Based_on_OWASP_Benchmark.pdf)

#### General
- Published 
- OWASP Areas:
1. This study is about the owasp benchmark, which differs from owasp masvs and mastg, not sure if this study should be included?

#### Goal of paper:
1. 

#### Notes
-

#### Study result
-

### 12 [A Study of the Security and Privacy Risks in Health-Related Mobile Applications in India](https://ieeexplore.ieee.org/abstract/document/10932131)
file:[A_Study_of_the_Security_and_Privacy_Risks_in_Health-Related_Mobile_Applications_in_India.pdf](https://github.com/user-attachments/files/23166231/A_Study_of_the_Security_and_Privacy_Risks_in_Health-Related_Mobile_Applications_in_India.pdf)

#### General
- Published 
- OWASP Areas:
1. Deals with the owasp mobile top 10 risks, which differs from my literary recherche scope

#### Goal of paper:
1. 

#### Notes
-

#### Study result
-

### 13 [Mobile Application as a Critical Infrastructure Cyberattack Surface](https://elibrary.kubg.edu.ua/id/eprint/47127/)
file: [O_Mykhaylova_T_Fedynyshyn_A_Datsiuk, B_Fihol_H_Hulak_CPITS-II-2023_3050.pdf](https://github.com/user-attachments/files/23166288/O_Mykhaylova_T_Fedynyshyn_A_Datsiuk.B_Fihol_H_Hulak_CPITS-II-2023_3050.pdf)

#### General
- Published 2023 (in CEUR WS, good source I think)
- OWASP Areas:
1. Using static and dynamic analysis techniques to verify the OWASP MASVS security requirements for a postal service app

#### Goal of paper:
1. Assess the security of a postal service app using MASVS
2. Evaluate security using static and dynamic analyses techniques
3. Present the vulnerabilities discovered

#### Notes
- Once again OWASP MASVS is used as guidelines, with MobSF as a tool to do the actual tests.
- Used dynamic and static techniques.
- The application analyzed does not use trusted Certificate Authorities to protect user privacy.

#### Study result
- Found vulnerabilities:
1. absence of usage confirmation
2. improper authorization for critically important functions
3. Hardcoded secret strings in source code
4. usesCleartextTraffic in the Android app’s manifest file is set to true, which means that the use of unencrypted network traffic in the app is permitted.

### 14 ["Your Doctor is Spying on You": An Analysis of Data Practices in Mobile Healthcare Applications](https://arxiv.org/abs/2510.06015)
file: [doctor_spying_paper.pdf](https://github.com/user-attachments/files/23166347/doctor_spying_paper.pdf)

#### General
- Published 
- OWASP Areas:
1. 

#### Goal of paper:
1. 

#### Notes
-

#### Study result
-

### 15 [From Ignition Interlock Servicing to SAST: Effective Taint Analysis for .NET MAUI Blazor Hybrid Applications](https://www.diva-portal.org/smash/record.jsf?pid=diva2%3A1966105&dswid=-5022)
file: [ignition_interlock_paper.pdf](https://github.com/user-attachments/files/23166398/ignition_interlock_paper.pdf)

#### General
- Published 
- OWASP Areas:
1. 

#### Goal of paper:
1. 

#### Notes
-

#### Study result
-

### 16 [IoT Mobile Applications Pentesting Methodology and Results of Research](https://ieeexplore.ieee.org/abstract/document/10878964)
file: [IoT_Mobile_Applications_Pentesting_Methodology_and_Results_of_Research.pdf](https://github.com/user-attachments/files/23166551/IoT_Mobile_Applications_Pentesting_Methodology_and_Results_of_Research.pdf)

#### General
- Published 
- OWASP Areas:
1. 

#### Goal of paper:
1. 

#### Notes
-

#### Study result
-

### 17 [The Study of Improvement and Risk Evaluation for Mobile Application Security Testing](https://link.springer.com/chapter/10.1007/978-3-319-76451-1_23)
file: no download :)

#### General
- Published 
- OWASP Areas:
1. 

#### Goal of paper:
1. 

#### Notes
-

#### Study result
-

### 18 [ANALYSIS OF SECURITY VULNERABILITIES IN MOBILE APPLICATIONS](http://ir.ktu.edu.gh:8080/xmlui/bitstream/handle/123456789/74/KWAKU%20TIEKU-DADEY_B203210032_COMPUTER%20SCIENCE%20DEPT.pdf?sequence=1&isAllowed=y)
file: [analysis_sec_vuln_mob_app_paper.pdf](https://github.com/user-attachments/files/23166692/analysis_sec_vuln_mob_app_paper.pdf)

#### General
- Published 
- OWASP Areas:
1. 

#### Goal of paper:
1. 

#### Notes
-

#### Study result
-

### 19 [A Comprehensive Study on Static Application Security Testing (SAST) Tools for Android](https://ieeexplore.ieee.org/abstract/document/10738442)
file: [A_Comprehensive_Study_on_Static_Application_Security_Testing_SAST_Tools_for_Android.pdf](https://github.com/user-attachments/files/23166717/A_Comprehensive_Study_on_Static_Application_Security_Testing_SAST_Tools_for_Android.pdf)

#### General
- Published 
- OWASP Areas:
1. 

#### Goal of paper:
1. 

#### Notes
-

#### Study result
-

### 20 [Enhancing Password Manager Application Security By Root Detection with Usability and Security Evaluation](https://ieeexplore.ieee.org/abstract/document/10382115)
file: [Enhancing_Password_Manager_Application_Security_By_Root_Detection_with_Usability_and_Security_Evaluation.pdf](https://github.com/user-attachments/files/23166735/Enhancing_Password_Manager_Application_Security_By_Root_Detection_with_Usability_and_Security_Evaluation.pdf)

#### General
- Published 
- OWASP Areas:
1. 

#### Goal of paper:
1. 

#### Notes
-

#### Study result
-

### 21 [Understanding Hackers’ Work: An Empirical Study of Offensive Security Practitioners](https://ieeexplore.ieee.org/abstract/document/10179474)
file: [hackers_work_paper.pdf](https://github.com/user-attachments/files/23166800/hackers_work_paper.pdf)

#### General
- Published 
- OWASP Areas:
1. 

#### Goal of paper:
1. 

#### Notes
-

#### Study result
-











