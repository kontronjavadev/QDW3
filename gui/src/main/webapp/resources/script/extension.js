
// Copy & Paste für Zell- und Zeileninhalte einer Tabelle
function updateContextData(event, formPrefix = 'form', varPrefix = '') {
// Für eingebettete Panels ist das formPrefix form:tabView2 und wenn ein Dialog mehrere Panels eingebettet hat, gibt es einen Konflikt mit gleichen
// Variablennamen. Daher müssen die Variablen einen Präfix (oder Postfix) haben, um eindeutig zu sein. Diesen gbt man ebenfalls an.
	const target = event?.originalEvent?.target;
	if (!target) return;

	const cell = target.closest('td');
	const row = cell?.closest('tr');
	if (!cell || !row) return;

	// Einzelzellenwert
	const cellValue = getCellValue(cell);

	// CSV-Zeile
	const rowCsv = Array.from(row.children)
		.map(getCellValue)
		.map(val => '"' + val.replace(/"/g, '""') + '"'
	).join(',');

	// Nutze JSF-spezifische ID-Namen
	document.querySelector('[name="' + formPrefix + ':' + varPrefix + 'cellText"]').value = cellValue;
	document.querySelector('[name="' + formPrefix + ':' + varPrefix + 'rowCsv"]').value = rowCsv;
}

function getCellValue(td) {
	if (!td) return "";

	const checkbox = td.querySelector('input[type="checkbox"]');
	if (checkbox) {
		return checkbox.checked ? "1" : "0";
	}

	const val = td.textContent.trim();
	return val;
}





//Tooltip-Behandlung
document.addEventListener('DOMContentLoaded', function() {
	// 1. Tooltip-Element genau einmal im gesamten Dokument erzeugen
	const tooltip = document.createElement('div');
	tooltip.id = 'instant-custom-tooltip';
	document.body.appendChild(tooltip);

	// 2. Event Delegation: Wir lauschen global auf alle Maus-Bewegungen
	document.addEventListener('mouseover', function(e) {
		// closest() sucht am Element selbst ODER wandert den DOM-Baum nach oben,
		// bis es ein Element mit der Klasse findet.
		const target = e.target ? e.target.closest('.pf-tooltip-target') : null;

		if (target) {
			// Text aus dem 'title'-Attribut holen. 
			// Wir benennen es sofort in 'data-title' um, damit der träge, weiße Browser-Tooltip nicht mehr erscheint.
			let text = target.getAttribute('title');
			if (text) {
				// erster hover: Attribut löschen, damit nicht der Browser den Tooltip anzeigt, sondern wir hier und in schön; Wert in data-title merken!  
				target.setAttribute('data-title', text);
				target.removeAttribute('title');
			} else {
				// nachfolgende hover oder wenn kein title angegeben war: Wert aus gemerktem data-title verwenden oder ersatzweise Zellinhalt verwenden.
				// So muss nur ein title angegeben werden, wenn er vom Zellinhalt abweicht. Wir wollen das nur bei Spaltenüberschriften, wo wir eine Abkürzung
				// auflösen. Bei Zellen schneiden wir den Text ab und wollen im Tooltip den komlpetten Text sehen.
				text = target.getAttribute('data-title') || target.textContent.trim();
			}
			// Ist kein Titel gesetzt, den Zell-Inhalt für den Tooltip verwenden
			if (!text) {
				text = target.textContent.trim();
			}


			 // Wir prüfen das Element selbst (span) und vorsichtshalber die Zelle (td), 
			// je nachdem, wo das CSS "overflow: hidden" genau greift.
			const isHeader = target.closest('th') !== null;
			const isToken = target.classList.contains('ui-autocomplete-token');
			let shouldShow = isHeader || isToken;
			if (!shouldShow) {
				const parentCell = target.closest('td') || target.parentElement;
				shouldShow = (target.scrollWidth > target.offsetWidth) || 
							 (parentCell && parentCell.scrollWidth > parentCell.offsetWidth);
			}

			// Wenn Text vorhanden ist UND er abgeschnitten wurde, Tooltip sofort anzeigen
			if (text && shouldShow) {
				tooltip.textContent = text;
				tooltip.style.display = 'block';
				
				// Position berechnen: Zentriert über der Zelle
				const rect = target.getBoundingClientRect();
				
				// TOP: Y-Koordinate der Zelle + Scroll-Offset - Höhe des Tooltips - 6px Pfeil - 3px Abstand
				const topPos = rect.top + window.scrollY - tooltip.offsetHeight - 9;

				// LEFT (zentriert zum Text): X-Koordinate der Zelle + Scroll-Offset + Hälfte der Zellbreite - Hälfte der Tooltipbreite
				// const leftPos = rect.left + window.scrollX + (rect.width / 2) - (tooltip.offsetWidth / 2);
				// LEFT (linksbündig zur Zelle) + Offset für Padding der Zelle
				let leftPos = rect.left + window.scrollX - 10;
				let arrowPos = 30; // 30px Einzug für den Pfeil


				// >--- KOLLISIONSERKENNUNG (Rechter Rand) --->
				// Wo würde die rechte Kante des Tooltips auf dem Bildschirm landen?
				const tooltipRightEdge = leftPos + tooltip.offsetWidth;
				// Wo ist das absolute Limit des Browserfensters? (minus 15px Sicherheitsabstand)
				const maxRightEdge = window.innerWidth + window.scrollX - 15;

				if (tooltipRightEdge > maxRightEdge) {
					const overflow = tooltipRightEdge - maxRightEdge;
					leftPos = leftPos - overflow;   // Kasten nach links schieben
					arrowPos = arrowPos + overflow; // Nase nach rechts schieben, damit sie über dem Text bleibt
				}
				// <--- KOLLISIONSERKENNUNG (Rechter Rand) ---<


				tooltip.style.top = topPos + 'px';
				tooltip.style.left = leftPos + 'px';
				tooltip.style.setProperty('--arrow-pos', arrowPos + 'px');
			}
		}
	});

	// 3. Wenn die Maus das Element verlässt, sofort verstecken
	document.addEventListener('mouseout', function(e) {
		const target = e.target ? e.target.closest('.pf-tooltip-target') : null;
		// WICHTIG: Autocomplete-Mehrfachauswahl: Pille:
		// Wenn wir von der Box auf den Text in der Box fahren, feuert der Browser 
		// auch ein mouseout. Wir dürfen den Tooltip aber nur ausblenden, wenn die Maus
		// unser gesamtes Token (inkl. Kinder) WIRKLICH verlässt.
		if (target && (!e.relatedTarget || !target.contains(e.relatedTarget))) {
			tooltip.style.display = 'none';
		}
	});
});





// Zweck: Mehrfachauswahl für Filterung. Ist alles ausgewählt, hat das dieselbe Bedeutung, wie wenn nichts ausgewählt ist,
// nämlich dass nicht gefiltert wird.
// Zeigt lediglich das Label an, wenn alles ausgewählt ist, anstatt alle einzelnen Items aufzulisten. 
document.addEventListener('DOMContentLoaded', function() {
	if (PrimeFaces.widget.SelectCheckboxMenu) {
		PrimeFaces.widget.SelectCheckboxMenu = PrimeFaces.widget.SelectCheckboxMenu.extend({
			updateLabel: function() {
				// Prüfen, ob dieses spezifische Menü die Steuer-Klasse "filter-all-or-none" hat.
				// Die Auswahl dieses Checkbox-Menü dient zur Filterung. Ist nichts ausgewählt, wird nicht gefiltert.
				// Ist alles ausgewählt, wird alles verwendet, also ebenfalls nicht gefiltert.
				if (this.jq.hasClass('filter-all-or-none')) {
					var checkedCount = this.inputs.filter(':checked').length;
					var totalCount = this.inputs.length;
	
					if (checkedCount === 0 || checkedCount === totalCount) {
						// Bei 0 oder allen Elementen: Fixen Text setzen.
						// Wir greifen dynamisch auf die in der Komponente konfigurierten Labels zurück.
						var fallbackLabel = this.cfg.label || this.cfg.emptyLabel;
						this.label.text(fallbackLabel);
					} else {
						// Bei teilweiser Auswahl: Das Original-Verhalten von PrimeFaces 
						// aufrufen, welches die kommaseparierte Liste generiert.
						this._super();
					}
				} else {
					// Für alle anderen SelectCheckboxMenus ohne diese Klasse: Standardverhalten.
					this._super();
				}
			}
		});
	}
});





// Erlaubt das Einfügen einer fertigen Liste an ";;"-separierten Einträgen in ein multiple-autocomplete-Feld
document.addEventListener("DOMContentLoaded", function() {
	// Event Delegation: Hängt am document, lauscht aber nur auf die inneren Inputs der AutoComplete-Komponenten
	document.addEventListener("paste", function(e) {
		// Prüft, ob das Element, in das eingefügt wurde, unser PrimeFaces-Input ist
		const target = e.target;

		// Prüft, ob ein Input-Feld das Ziel ist UND ob es innerhalb des Multiple-AutoComplete-Containers liegt
		if (target && target.tagName === 'INPUT' && target.closest('.ui-autocomplete-multiple')) {
			const pasteData = (e.clipboardData || window.clipboardData).getData('text');
			const separatorRegex = /(?:;;|[;,\n\r\t])+/;

			if (pasteData && separatorRegex.test(pasteData)) {
				// Blockiert die Standard-Eingabe des Browsers, bevor PrimeFaces reagieren kann
				e.preventDefault();

				const tokens = pasteData.split(separatorRegex)
					.map(t => t.trim())
					.filter(t => t.length > 0);

				if (tokens.length > 0) {
					// Prüft zur Sicherheit, ob das p:remoteCommand existiert und ruft es auf
					if (typeof processPastedMaterials === "function") {
						// NEU: Wir lesen target.id aus und übergeben es als weiteren Parameter
						processPastedMaterials([
							{name: 'targetFieldId', value: target.id},
							{name: 'pastedTokens', value: tokens.join(';;')}
						]);
					} else {
						console.error("PrimeFaces remoteCommand 'processPastedMaterials' wurde nicht gefunden.");
					}
				}
			}
		}
	});
});





// Globale Hilfsfunktion für PrimeFaces Token-Tooltips bei Mehrfach-Autocomplete
function addTooltipToToken(fieldId, tokenCode, tooltipText) {
    setTimeout(function() {
        document.querySelectorAll('[id$="' + fieldId + '"] .ui-autocomplete-token-label').forEach(function(span) {
            if (span.textContent.trim() === tokenCode) {
                var token = span.parentElement;
                
                // 1. Die Klasse hinzufügen, auf die dein Tooltip-Skript lauscht
                token.classList.add('pf-tooltip-target');
                
                // 2. Den kompletten Namen direkt in data-title schreiben,
                // damit der Browser-Standard-Tooltip garantiert nicht aufblitzt.
                token.setAttribute('data-title', tooltipText);
            }
        });
    }, 50);
}

