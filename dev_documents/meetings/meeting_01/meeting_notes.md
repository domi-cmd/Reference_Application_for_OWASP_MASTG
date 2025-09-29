google scholar android apps vulnerable by design
welche kategorien möchte ich implementieren
welche findet ein scanner und welche nicht
ausloten was kann ein solches tool und welche nicht

schauen was wurde schon gemacht bei apps, damit ich 
auch neues tun statt gleiches implementieren

related work section maximal 5 seiten, gute hat 2 bis 3
bischen schauen was es schon gibt, klassifzieren, und wie man das suchen tut
systematic literature review (chatgpt) nicht so extrem wie bei grossen publikationen 10 bis 20 referenzen bei bachelorarbeit

https://github.com/Ostorlab/benchmarks

---------------------------------------------



A systematic literature review (SLR) is a structured, methodical way of surveying existing research on a topic. Instead of just collecting and 
summarizing papers I find, it follows a rigorous process to ensure that:

The search is comprehensive and reproducible – I define search terms, databases (like IEEE Xplore, ACM DL, Springer, Google Scholar), and 
inclusion/exclusion criteria, so someone else could repeat my review.

Paper selection is transparent – I state why I included or excluded specific studies (e.g., only papers after 2010, only peer-reviewed, only 
about Android security).

Data extraction is systematic – I extract consistent information from each study (like methodology, results, limitations).

Synthesis is structured – instead of just summarizing, I categorize and compare the studies to identify trends, gaps, and consensus in the field.

In my case—building Android apps that are vulnerable by design—an SLR would mean:

Defining research questions, e.g.,

What approaches exist for intentionally insecure apps (like DVIA, InsecureBank, Damn Vulnerable Web App equivalents)?

How are they used in teaching, testing, or security research?

What vulnerabilities do they cover (e.g., insecure storage, WebView misuse, cryptography issues)?

Planning search queries, like "vulnerable android app", "intentionally insecure application", "mobile security education tools".

Screening results systematically (removing duplicates, filtering out irrelevant papers).

Creating a table with key details of each relevant work (year, scope, vulnerabilities covered, audience, evaluation).

Writing the related work section based on that structured comparison.

It’s basically about making my literature review methodical and defendable, not just “here are some papers I found.”



------------------------------------------------



Here’s a sketch of a Systematic Literature Review (SLR) protocol tailored to my bachelor thesis topic (Android apps vulnerable by design). This is 
usually what I’d document in my methodology section before actually doing the review:

1. Define Research Questions (RQs)

Examples:

RQ1: What vulnerable-by-design Android applications have been developed and documented in academic or industry literature?

RQ2: For what purposes are these apps used (e.g., education, penetration testing, research, training)?

RQ3: What kinds of vulnerabilities are intentionally implemented (e.g., insecure storage, weak cryptography, improper authentication, WebView misuse)?

RQ4: How are these apps evaluated or validated (e.g., in classrooms, research labs, security exercises)?

RQ5: What limitations or challenges are identified (e.g., maintenance, realism, coverage of CVEs)?

2. Search Strategy

Databases: IEEE Xplore, ACM Digital Library, SpringerLink, ScienceDirect, Google Scholar.

Search strings (examples):

"vulnerable android app"

"intentionally insecure mobile application"

"android security education tool"

"android penetration testing training"

Use Boolean operators: ("android" AND ("vulnerable" OR "intentionally insecure") AND ("app" OR "application")).

3. Inclusion/Exclusion Criteria

Include:

Papers describing intentionally vulnerable Android apps or frameworks.

Papers on teaching/training mobile security using vulnerable apps.

Peer-reviewed publications (conference, journal, workshop).

Exclude:

Work on unintentional vulnerabilities (real-world malware, app audits).

Non-Android platforms (unless directly comparable, e.g., iOS but rare).

Non-English publications.

Papers without accessible full text.

4. Study Selection Process

Step 1: Collect all results from database searches.

Step 2: Remove duplicates.

Step 3: Screen titles/abstracts using inclusion/exclusion criteria.

Step 4: Read full texts to confirm relevance.

Step 5: Record final included studies in a reference manager (e.g., Zotero, Mendeley).

5. Data Extraction Plan

Create a table or spreadsheet. For each paper, extract:

Bibliographic details (author, year, venue).

Vulnerable app/tool name.

Purpose (education, testing, research).

Vulnerabilities covered (categories or OWASP Mobile Top 10).

Evaluation (user studies, experiments, case studies).

Reported limitations.

6. Synthesis

Group by purpose (education, research, testing).

Map vulnerabilities covered against a known taxonomy (e.g., OWASP Mobile Top 10).

Identify trends (e.g., most apps are for education, few cover cryptography issues).

Highlight gaps (e.g., lack of long-term maintenance, limited real-world realism).

This SLR protocol doesn’t have to be super formal at bachelor level, but even a shortened version (RQ + search strategy + inclusion criteria + summary table) will impress my supervisor because it shows rigor.



-------------------------------------------



Here’s a **template for a summary table** for my systematic literature review on intentionally vulnerable Android apps. I can fill it in as I go through each paper:

| No. | Paper / Source       | Year | Vulnerable App / Tool              | Purpose / Use Case              | Vulnerabilities Covered                      | Evaluation / Methodology             | Notes / Limitations                         |
| --- | -------------------- | ---- | ---------------------------------- | ------------------------------- | -------------------------------------------- | ------------------------------------ | ------------------------------------------- |
| 1   | Author et al., Title | 2021 | DVIA (Damn Vulnerable iOS/Android) | Education / Security Training   | Insecure storage, weak crypto, improper auth | Student lab exercises, case studies  | Limited coverage of network vulnerabilities |
| 2   | Author et al., Title | 2020 | InsecureBank                       | Penetration Testing / Education | SQL injection, XSS, authentication flaws     | Lab experiments, penetration testing | Only simulates banking scenarios            |
| 3   | Author et al., Title | 2019 | Mobile Security Lab App            | Research / Teaching             | OWASP Mobile Top 10 coverage                 | User studies with students           | Small sample size, only academic use        |
| …   | …                    | …    | …                                  | …                               | …                                            | …                                    | …                                           |

**How to use it effectively:**

1. **No.:** Simple numbering for easy reference.
2. **Paper / Source:** Full citation or shorthand for quick reference.
3. **Year:** Publication year to track trends.
4. **Vulnerable App / Tool:** Name of the intentionally vulnerable app.
5. **Purpose / Use Case:** Why it exists (education, testing, research).
6. **Vulnerabilities Covered:** Use categories like **OWASP Mobile Top 10** or a custom list for clarity.
7. **Evaluation / Methodology:** How the app/tool’s effectiveness was assessed.
8. **Notes / Limitations:** Anything important to highlight, like gaps or restrictions.

Once filled, I can **analyze trends** in my related work section:

* Which apps are most cited?
* Which vulnerabilities are commonly covered?
* Which purposes dominate the field?
* What gaps exist that my work might address?



--------------------------------------------



Since my **research question (RQ)** is about the **state of the art of current “vulnerable-by-design” Android apps**, the SLR should aim to **systematically map what apps exist, what vulnerabilities they cover, and how they’re used**. Here’s how I 
can adapt the previous table and methodology specifically for my RQ:

---

### 1. Refined Research Question

**RQ:** *What is the current state of the art of intentionally vulnerable Android apps, in terms of available tools, targeted vulnerabilities, and 
intended use?*

Sub-questions could be:

* **RQ1:** Which vulnerable-by-design Android apps currently exist?
* **RQ2:** What categories of vulnerabilities do these apps implement?
* **RQ3:** For what purposes are these apps used (education, research, penetration testing)?
* **RQ4:** How are they evaluated or validated in the literature?
* **RQ5:** Are there gaps or limitations in the current offerings?

---

### 2. Adapted SLR Table Template

| No. | App / Tool   | Year | Source / Paper      | Purpose / Use Case  | Vulnerabilities Covered        | Evaluation / Methodology    | Notes / Limitations                    |
| --- | ------------ | ---- | ------------------- | ------------------- | ------------------------------ | --------------------------- | -------------------------------------- |
| 1   | DVIA         | 2021 | Academic / Workshop | Security Education  | Insecure storage, crypto, auth | Lab exercises with students | Limited network vulnerability coverage |
| 2   | InsecureBank | 2020 | Conference Paper    | Penetration Testing | SQLi, XSS, auth flaws          | Pen testing exercises       | Only banking scenarios                 |
| 3   | AndroidGoat  | 2019 | GitHub / Paper      | Teaching / Training | OWASP Mobile Top 10            | Guided labs, workshops      | Some vulnerabilities outdated          |
| …   | …            | …    | …                   | …                   | …                              | …                           | …                                      |

---

### 3. How This Serves my RQ

* **Mapping the landscape:** I can visually and textually summarize which apps exist.
* **Coverage analysis:** Compare which vulnerabilities are commonly included and which are rare.
* **Purpose analysis:** Show whether the focus is on teaching, research, or testing.
* **Gap identification:** Highlight missing coverage (e.g., few apps for IoT-related Android apps, weak crypto coverage, outdated frameworks).

---

### 4. Tips for Writing the Related Work Section

1. Start by **briefly explaining my SLR methodology** (databases searched, keywords, inclusion/exclusion criteria).
2. Introduce the table, highlighting trends and notable apps.
3. Discuss patterns I observe: e.g., “Most vulnerable-by-design apps target education, with limited focus on penetration testing beyond simulated 
banking apps.”
4. Conclude with a **gap statement**, which can naturally lead into my own contribution: e.g., “No current app offers a comprehensive set of modern 
Android vulnerabilities for both education and research, motivating this work.”
